package xyz.siavash.instagramhelper.mvp.presenter.interfaces;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import xyz.siavash.instagramhelper.mvp.ui.interfaces.RelatedUsersViewInterface;

/**
 * Created by siavash on 6/19/16.
 */

public interface RelatedUserPresenterInterface extends MvpPresenter<RelatedUsersViewInterface> {
    void loadUsers(final boolean pullToRefresh);
}
