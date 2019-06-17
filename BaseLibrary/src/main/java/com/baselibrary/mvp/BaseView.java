package com.baselibrary.mvp;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * date:2019/1/4
 */
public interface BaseView {

    Context mContext();

    /**
     * 绑定生命周期
     *
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLifecycle();
}
