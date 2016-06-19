package xyz.siavash.instagramhelper.mvp.presenter.interfaces;


import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import xyz.siavash.instagramhelper.mvp.ui.interfaces.LoginViewInterface;

/**
 * Created by siavash on 4/28/16.
 */
public interface LoginPresenterInterface extends MvpPresenter<LoginViewInterface> {
    public void login();
}
