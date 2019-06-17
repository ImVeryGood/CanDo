package com.component.app.user.presenter;

import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.widget.Toast;

import com.baselibrary.mvp.BasePresenter;
import com.baselibrary.service.ApiManager;
import com.baselibrary.service.BaseBean;
import com.baselibrary.service.MObserver;
import com.component.R;
import com.component.app.user.bean.LoginBean;
import com.component.app.user.view.LoginView;
import com.component.bean.BannerBean;
import com.component.net.RetrofitManage;
import com.component.utils.IconfontTextView;
import com.google.gson.Gson;

/**
 * date:2019/6/10
 * describe：
 */
public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(LoginView view) {
        super(view);
    }

    @Override
    protected void init() {

    }

    public void login(String userName, String passWord) {
        Log.d("SSSSSSSS", "login: " + userName + ":" + passWord);
        RetrofitManage.getInstance().login(userName, passWord)
                .compose(ApiManager.<BaseBean<LoginBean>>toMainThread())
                .compose(ApiManager.<LoginBean>filterData())
                .subscribe(new MObserver<LoginBean>(null) {
                    @Override
                    protected void success(LoginBean baseBean) {
                        view.loginSuccess();
                    }

                    @Override
                    protected void error(String error) {
                        Toast.makeText(view.mContext(), error, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void setEye(TextInputEditText inputEditText, IconfontTextView textView) {
        /**
         * 129 不可见
         * 128可见
         */
        if (inputEditText.getInputType() == 129) {
            inputEditText.setInputType(128);
            textView.setText(view.mContext().getResources().getString(R.string.icon_eye_see));
        } else {
            inputEditText.setInputType(129);
            textView.setText(view.mContext().getResources().getString(R.string.icon_eye_close));
        }
        inputEditText.setSelection(inputEditText.getText().length());
    }

}
