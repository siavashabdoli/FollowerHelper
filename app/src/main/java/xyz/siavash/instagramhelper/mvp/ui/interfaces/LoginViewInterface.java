package xyz.siavash.instagramhelper.mvp.ui.interfaces;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by siavash on 4/28/16.
 */
public interface LoginViewInterface extends MvpView {

    void LoginSuccessful();
    void showLoading();
    void setErrorAndHideLoading(int errorResourceId);
}
