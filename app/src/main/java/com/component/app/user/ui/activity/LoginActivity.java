package com.component.app.user.ui.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.baselibrary.mvp.BaseActivity;
import com.component.R;
import com.component.app.main.MainActivity;
import com.component.app.user.presenter.LoginPresenter;
import com.component.app.user.view.LoginView;
import com.component.utils.IconfontTextView;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.user_name)
    TextInputEditText userName;
    @BindView(R.id.pass_word)
    TextInputEditText passWord;
    @BindView(R.id.eye_password)
    IconfontTextView eyePassword;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @OnClick({R.id.login, R.id.register, R.id.eye_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                presenter.login(userName.getText().toString(), passWord.getText().toString());
                break;
            case R.id.register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.eye_password:
                presenter.setEye(passWord, eyePassword);
                break;
        }
    }
}
