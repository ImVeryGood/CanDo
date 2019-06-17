package com.component.app.user.ui;

import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.baselibrary.mvp.BaseActivity;
import com.baselibrary.utils.CodeUtils;
import com.baselibrary.view.MTitleBar;
import com.component.R;
import com.component.app.user.presenter.RegisterPresenter;
import com.component.app.user.view.RegisterView;
import com.component.utils.IconfontTextView;
import com.component.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView, View.OnClickListener {

    @BindView(R.id.code_image)
    ImageView codeImage;
    @BindView(R.id.user_name)
    TextInputEditText userName;
    @BindView(R.id.pass_word)
    TextInputEditText passWord;
    @BindView(R.id.confirm_pass_word)
    TextInputEditText confirmPassWord;
    @BindView(R.id.code)
    TextInputEditText code;
    @BindView(R.id.eye_password)
    IconfontTextView eyePassword;
    @BindView(R.id.eye_password_confirm)
    IconfontTextView eyePasswordConfirm;
    @BindView(R.id.title_bar)
    MTitleBar titleBar;
    @BindView(R.id.register)
    Button register;
    private CodeUtils codeUtils;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void onCreate() {
        createCode();
        initListener();
    }

    private void initListener() {
        titleBar.backClick(this);
    }

    @Override
    public RegisterPresenter initPresenter() {
        return new RegisterPresenter(this);
    }

    @OnClick({R.id.code_image, R.id.register, R.id.eye_password_confirm, R.id.eye_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.code_image:
                createCode();
                break;
            case R.id.register:
                presenter.register(userName.getText().toString(),
                        passWord.getText().toString(),
                        confirmPassWord.getText().toString(),
                        code.getText().toString(),
                        codeUtils.getCode());
                break;
            case R.id.eye_password_confirm:
                presenter.setEye(confirmPassWord, eyePasswordConfirm);
                break;
            case R.id.eye_password:
                presenter.setEye(passWord, eyePassword);
                break;
        }
    }

    public void createCode() {
        codeUtils = CodeUtils.getInstance();
        codeImage.setImageBitmap(codeUtils.createBitmap());

    }

    @Override
    public void registerSuccess() {
        ToastUtils.showToast(this, R.string.register_success);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
