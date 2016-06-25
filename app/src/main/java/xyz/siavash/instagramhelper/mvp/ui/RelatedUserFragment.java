package xyz.siavash.instagramhelper.mvp.ui;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.siavash.instagramhelper.R;
import xyz.siavash.instagramhelper.mvp.presenter.RelatedUserPresenter;
import xyz.siavash.instagramhelper.mvp.presenter.interfaces.RelatedUserPresenterInterface;
import xyz.siavash.instagramhelper.mvp.ui.adapter.RelatedUserAdapter;
import xyz.siavash.instagramhelper.mvp.ui.interfaces.RelatedUsersViewInterface;
import xyz.siavash.instagramhelper.mvp.uimodel.UserObject;

/**
 * Created by siavash on 6/20/16.
 */

public class RelatedUserFragment extends MvpLceFragment<SwipeRefreshLayout,List<UserObject>,RelatedUsersViewInterface,RelatedUserPresenterInterface>
        implements SwipeRefreshLayout.OnRefreshListener,RelatedUsersViewInterface {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    RelatedUserAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_related,container,false);
        ButterKnife.bind(this,view);
        contentView.setOnRefreshListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData(false);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @NonNull
    @Override
    public RelatedUserPresenterInterface createPresenter() {
        return new RelatedUserPresenter();
    }

    @Override
    public void setData(List<UserObject> data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadUsers(pullToRefresh);
    }
}
