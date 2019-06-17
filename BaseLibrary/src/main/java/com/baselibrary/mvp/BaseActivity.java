package com.baselibrary.mvp;

import android.content.Context;
import android.os.Bundle;

import com.baselibrary.skin.MySkinFactory;
import com.baselibrary.skin.SkinManager;
import com.baselibrary.utils.ActivityManager;
import com.baselibrary.utils.FitStateUI;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity implements BaseView, SkinManager.SkinUpdateListener {
    protected P presenter;
    public Context mContext;
    private Unbinder unbinder;
    private MySkinFactory mySkinFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mySkinFactory = new MySkinFactory();
        getLayoutInflater().setFactory(mySkinFactory);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mContext = this;
        presenter = initPresenter();
        FitStateUI.setImmersionStateMode(this);
        unbinder = ButterKnife.bind(this);
        ActivityManager.getAppInstance().addActivity(this);
        SkinManager.getInstance().addSkinUpdateListener(this);
        onCreate();
    }

    /**
     * @return 布局id
     */
    public abstract int getLayoutId();

    /**
     * onCreate 方法
     */
    public abstract void onCreate();

    @Override
    public Context mContext() {
        return mContext;
    }

    public abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (presenter != null) {
            presenter.detach();
        }
        ActivityManager.getAppInstance().removeActivity(this);
    }

    @Override
    public void onSkinUpdate() {
        mySkinFactory.apply();
    }
}
