package com.component.app.fragment;


import android.support.v4.app.Fragment;

import com.baselibrary.mvp.BaseFragment;
import com.baselibrary.mvp.BasePresenter;
import com.component.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onCreateView() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

}
