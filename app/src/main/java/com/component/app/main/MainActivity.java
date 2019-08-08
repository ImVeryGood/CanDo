package com.component.app.main;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.baselibrary.mvp.BaseActivity;
import com.baselibrary.mvp.BasePresenter;
import com.component.R;
import com.component.app.fragment.HomeFragment;
import com.component.app.fragment.KnowledgeFragment;
import com.component.app.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    private HomeFragment homeFragment;
    private KnowledgeFragment knowledgeFragment;
    private MineFragment mineFragment;
    private List<Fragment> list;
    private int index=0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreate() {
        init();

    }

    private void init() {
        list = new ArrayList<>();
        homeFragment = new HomeFragment();
        knowledgeFragment = new KnowledgeFragment();
        mineFragment = new MineFragment();
        list.add(homeFragment);
        list.add(knowledgeFragment);
        list.add(mineFragment);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, homeFragment)
                .show(homeFragment)
                .commit();

        bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.tab_main_pager:
                switchFragment(0);
                break;
            case R.id.tab_knowledge_hierarchy:
                switchFragment(1);
                break;
            case R.id.tab_navigation:
                switchFragment(2);
                break;
        }
        return true;
    }

    public void switchFragment(int currentIndex) {
        if (index == currentIndex) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(list.get(index));
        if (list.get(currentIndex).isAdded()) {
            transaction.show(list.get(currentIndex));
        } else {
            transaction.add(R.id.frame_layout, list.get(currentIndex)).show(list.get(currentIndex));
        }
        transaction.commit();
        index = currentIndex;
    }
}
