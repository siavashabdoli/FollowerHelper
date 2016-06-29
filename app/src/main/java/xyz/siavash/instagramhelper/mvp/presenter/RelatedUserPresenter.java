package xyz.siavash.instagramhelper.mvp.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import xyz.siavash.instagramhelper.mvp.presenter.interfaces.RelatedUserPresenterInterface;
import xyz.siavash.instagramhelper.mvp.ui.interfaces.RelatedUsersViewInterface;
import xyz.siavash.instagramhelper.mvp.uimodel.UserObject;
import xyz.siavash.instagramhelper.network.ApiProvider;
import xyz.siavash.instagramhelper.network.ApiService;
import xyz.siavash.instagramhelper.network.logicmodel.UserItem;
import xyz.siavash.instagramhelper.network.logicmodel.UsersItem;
import xyz.siavash.instagramhelper.util.UserDataPreferences;
import xyz.siavash.instagramhelper.util.UserSortComprator;

/**
 * Created by siavash on 6/19/16.
 */

public class RelatedUserPresenter extends MvpBasePresenter<RelatedUsersViewInterface> implements RelatedUserPresenterInterface {
    private final ApiService instaService;
    private CompositeSubscription mSubscriptions;


    public RelatedUserPresenter(){
        instaService= ApiProvider.getTService();
        mSubscriptions=new CompositeSubscription();
    }
    @Override
    public void loadUsers(final boolean pullToRefresh) {
        getView().showLoading(pullToRefresh);
        String token=UserDataPreferences.getToken();
        Observable<UsersItem> followersObservable = instaService
                .myFollowers(token)
                .subscribeOn(Schedulers.newThread());
        Observable<UsersItem> followsObservable = instaService
                .myFollows(token)
                .subscribeOn(Schedulers.newThread());
        Observable<List<UserObject>> combined = Observable.zip(
                followsObservable,
                followersObservable,
                 new Func2<UsersItem, UsersItem, List<UserObject>>() {
            @Override
            public List<UserObject> call(UsersItem followings, UsersItem followers) {
                List<UserObject> userObjectList= new ArrayList<UserObject>();
                Collections.sort(followings.userItemList,new UserSortComprator());
                Collections.sort(followers.userItemList,new UserSortComprator());
                int followingIndex=0;
                        for (UserItem followerUser :
                                followers.userItemList) {
                            UserObject userObject;

                            if(followingIndex<followings.userItemList.size()) {
                                UserItem followingUser=followings.userItemList.get(followingIndex);
                                while (followerUser.userName.compareTo(followingUser.userName)>0
                                        && followingIndex<followings.userItemList.size()){

                                    userObjectList.add(UserObject.createFromUserItem(followingUser,"following"));
                                    followingIndex++;
                                    followingUser=followings.userItemList.get(followingIndex);

                                }
                                    if (followerUser.userID == followingUser.userID) {
                                        userObject = UserObject.createFromUserItem(followerUser, "friend");
                                        followingIndex++;
                                    } else{
                                        userObject = UserObject.createFromUserItem(followerUser, "follower");
                                    }

                            }else {
                                userObject=UserObject.createFromUserItem(followerUser,"follower");
                            }




                            userObjectList.add(userObject);

                        }
                return userObjectList;
            }
        });

        mSubscriptions.add(combined
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UserObject>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        e.printStackTrace();

                        if (e instanceof HttpException) {
                            ResponseBody responseBody = ((HttpException) e).response().errorBody();
                            if(responseBody!=null)
                                try {
                                    Log.d("error",responseBody.string());
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }

                        }
                        if(isViewAttached()){
                            getView().showError(e,pullToRefresh);
                        }

                    }

                    @Override
                    public void onNext(List<UserObject> userObjects) {
                        if(isViewAttached()){
                            getView().setData(userObjects);
                            getView().showContent();
                        }
                    }

                })
        );

    }

    @Override
    public void attachView(RelatedUsersViewInterface view) {
        super.attachView(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        mSubscriptions.clear();
    }
}
