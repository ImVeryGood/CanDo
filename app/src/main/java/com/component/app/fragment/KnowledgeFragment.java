package com.component.app.fragment;


import android.support.v4.app.Fragment;

import com.baselibrary.mvp.BaseFragment;
import com.baselibrary.mvp.BasePresenter;
import com.component.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowledgeFragment extends BaseFragment {


    @Override
    public int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    public void onCreateView() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

}
