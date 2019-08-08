package com.component;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baselibrary.mvp.BaseActivity;
import com.baselibrary.mvp.BasePresenter;
import com.baselibrary.service.ApiManager;
import com.baselibrary.service.MObserver;
import com.baselibrary.skin.SkinManager;
import com.component.app.home.bean.BannerBean;
import com.component.net.RetrofitManage;
import com.google.gson.Gson;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;


public class ThemeActivity extends BaseActivity {

    @BindView(R.id.change_theme)
    Button changeTheme;
    @BindView(R.id.change_recover)
    Button changeRecover;

    @Override
    public int getLayoutId() {
        return R.layout.activity_theme;
    }

    @Override
    public void onCreate() {


    }
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @OnClick({R.id.change_theme, R.id.change_recover,R.id.change_theme_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change_theme:
                SkinManager.getInstance()
                        .loadSkin(Environment.getExternalStorageDirectory()
                                .getAbsolutePath()+ File.separator+"blue-skin.skin");
                break;
            case R.id.change_theme_reset:
                SkinManager.getInstance().restoreDefaultTheme();
                break;
            case R.id.change_recover:
              String url="http://mrh-m.bhuidai.com";
                Log.d("SSSSSSSSSSS", "onViewClicked: "+url.substring(url.indexOf(".")+1));
                break;
        }
    }

    public void getBanner() {
        RetrofitManage.getInstance()
                .bannerData()
                .compose(ApiManager.<BannerBean>toMainThread())
                .subscribe(new MObserver<BannerBean>(null) {
                    @Override
                    protected void success(BannerBean bannerBean) {
                        Log.d("SSSSSSSS", "success: "+new Gson().toJson(bannerBean));
                    }

                    @Override
                    protected void error(String error) {
                        Log.d("SSSSSS", "error: "+error);
                    }
                });

    }


}
