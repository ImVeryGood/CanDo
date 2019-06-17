package com.baselibrary.mvp;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.trello.rxlifecycle2.components.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * BaseFragment 基类
 */
public abstract class BaseFragment<P extends BasePresenter> extends RxFragment implements BaseView {
    protected P presenter;
    protected View view;
    protected Unbinder unbinder;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = initPresenter();
        view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        onCreateView();
        return view;
    }

    @Override
    public Context mContext() {
        return getActivity();
    }

    public abstract int getLayoutId();

    public abstract void onCreateView();

    public abstract P initPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
