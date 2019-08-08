package com.component.app.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.baselibrary.mvp.BaseFragment;
import com.component.R;
import com.component.app.home.adapter.RecyclerAdapter;
import com.component.app.home.bean.ArticleBean;
import com.component.app.home.bean.BannerBean;
import com.component.app.home.presenter.HomePresenter;
import com.component.app.home.view.GlideImageLoader;
import com.component.app.home.view.HomeView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView, OnBannerListener {

    @BindView(R.id.y_banner)
    Banner yBanner;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onCreateView() {
        initAdapter();
    }

    private void initAdapter() {
        adapter = new RecyclerAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

//    private void initBanner(List<BannerBean.DataBean> list) {
//        banner.startTurning(3000);
//        banner.setPages(new CBViewHolderCreator<BannerHolder>() {
//            @Override
//            public BannerHolder createHolder() {
//                return new BannerHolder();
//            }
//        }, list);
//        banner.setOnItemClickListener(this);
//    }

    @Override
    public HomePresenter initPresenter() {
        return new HomePresenter(this);
    }


    @Override
    public void showBanner(BannerBean bannerBean, List<String> list) {
        yBanner.setImageLoader(new GlideImageLoader());
        yBanner.setImages(list);
        yBanner.start();
        yBanner.setOnBannerListener(this);
    }

    @Override
    public void showArticleList(ArticleBean articleBean) {
        adapter.setData(articleBean.getData().getDatas());
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
    }


}
