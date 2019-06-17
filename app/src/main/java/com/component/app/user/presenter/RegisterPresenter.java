package com.component.app.user.presenter;

import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.widget.Toast;

import com.baselibrary.mvp.BasePresenter;
import com.baselibrary.service.ApiManager;
import com.baselibrary.service.BaseBean;
import com.baselibrary.service.MObserver;
import com.component.R;
import com.component.app.user.bean.LoginBean;
import com.component.app.user.view.RegisterView;
import com.component.net.RetrofitManage;
import com.component.utils.IconfontTextView;
import com.component.utils.ToastUtils;

/**
 * date:2019/6/11
 * describe：
 */
public class RegisterPresenter extends BasePresenter<RegisterView> {
    public RegisterPresenter(RegisterView view) {
        super(view);
    }

    @Override
    protected void init() {

    }

    public void register(String userName, String passWord, String confirmPassWord, String code, String createCode) {
        if (TextUtils.isEmpty(userName)) {
            ToastUtils.showToast(view.mContext(), R.string.tips_empty);
            return;
        } else if (TextUtils.isEmpty(passWord)) {
            ToastUtils.showToast(view.mContext(), R.string.tips_empty);
            return;
        } else if (TextUtils.isEmpty(confirmPassWord)) {
            ToastUtils.showToast(view.mContext(), R.string.tips_empty);
            return;
        } else if (TextUtils.isEmpty(code)) {
            ToastUtils.showToast(view.mContext(), R.string.tips_code);
            return;
        } else if (!passWord.equals(confirmPassWord)) {
            ToastUtils.showToast(view.mContext(), R.string.tips_no_equals);
            return;
        } else if (!code.equals(createCode)) {
            ToastUtils.showToast(view.mContext(), R.string.tips_code_error);
            return;
        }
        RetrofitManage.getInstance().register(userName, passWord, passWord)
                .compose(ApiManager.<BaseBean<LoginBean>>toMainThread())
                .compose(ApiManager.<LoginBean>filterData())
                .subscribe(new MObserver<LoginBean>(null) {
                    @Override
                    protected void success(LoginBean baseBean) {
                        view.registerSuccess();
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
