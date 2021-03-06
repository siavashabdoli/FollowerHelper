package xyz.siavash.instagramhelper.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.siavash.instagramhelper.R;
import xyz.siavash.instagramhelper.mvp.presenter.LoginPresenter;
import xyz.siavash.instagramhelper.mvp.presenter.interfaces.LoginPresenterInterface;
import xyz.siavash.instagramhelper.mvp.ui.interfaces.LoginViewInterface;

/**
 * Created by siavash on 4/28/16.
 */
public class LoginActivity extends MvpActivity<LoginViewInterface, LoginPresenterInterface> implements LoginViewInterface  {

    private static String TAG;

    @Bind(R.id.login_activity_btn_login)
    AppCompatButton btnLogin;

    @Bind(R.id.loadingView)
    ProgressBar loadingView;

    @Bind(R.id.login_activity_error)
    TextView tvError;

    @NonNull
    @Override
    public LoginPresenterInterface createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TAG=getLocalClassName();
        ButterKnife.bind(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        btnLogin.setOnClickListener(mainListener);


    }
    View.OnClickListener mainListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.login_activity_btn_login:
                    presenter.login();
                    break;
                default:
                    Log.d(TAG,"can't find any function for that view");
            }
        }
    };


    @Override
    public void LoginSuccessful() {
        startActivity(new Intent(this,TabContainerActivity.class));
        finish();
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
        btnLogin.setClickable(false);
    }

    @Override
    public void setErrorAndHideLoading(int errorResourceId) {
        loadingView.setVisibility(View.GONE);
        tvError.setText(errorResourceId);
        btnLogin.setClickable(true);
    }
}
