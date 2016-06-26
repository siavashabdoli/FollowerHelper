package xyz.siavash.instagramhelper.mvp.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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
        mSubscriptions.add(instaService.myFollows(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<UsersItem, List<UserObject>>() {
                    @Override
                    public List<UserObject> call(UsersItem usersItem) {
                        List<UserObject> userObjectList= new ArrayList<UserObject>();
                        for (UserItem userTemp :
                                usersItem.userItemList) {
                            UserObject userObject=UserObject.createFromUserItem(userTemp,"test");
                            userObjectList.add(userObject);

                        }
                        return userObjectList;
                    }
                })
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
    }
}
