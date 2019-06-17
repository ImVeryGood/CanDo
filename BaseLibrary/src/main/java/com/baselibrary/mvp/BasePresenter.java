package com.baselibrary.mvp;


import com.android.tu.loadingdialog.LoadingDialog;

import java.lang.ref.WeakReference;

/**
 * date:2019/1/4
 */
public abstract class BasePresenter<V extends BaseView> {
    protected V view;
    private WeakReference<V> weakReference;
    private LoadingDialog.Builder builder;
    protected LoadingDialog dialog;

    public BasePresenter(V view) {
        weakReference = new WeakReference<>(view);
        this.view = weakReference.get();
        initLoading();
        init();
    }

    /**
     * 解绑
     */
    public void detach() {
        view = null;
        weakReference.clear();
        weakReference = null;
    }

    protected abstract void init();

    /**
     * 加载进度
     */
    public void initLoading() {
        builder = new LoadingDialog.Builder(view.mContext())
                .setCancelable(true)
                .setMessage("加载中...");
        dialog = builder.create();
    }
}
