package com.component.app.home.presenter;

import com.baselibrary.mvp.BasePresenter;
import com.baselibrary.service.ApiManager;
import com.baselibrary.service.MObserver;
import com.component.app.home.bean.ArticleBean;
import com.component.app.home.bean.BannerBean;
import com.component.app.home.view.HomeView;
import com.component.net.RetrofitManage;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/7/31
 * describe：
 */
public class HomePresenter extends BasePresenter<HomeView> {
    public HomePresenter(HomeView view) {
        super(view);
    }

    @Override
    protected void init() {
        bannerData();
        articleData();
    }

    public void bannerData() {
        RetrofitManage.getInstance().bannerData()
                .compose(ApiManager.<BannerBean>toMainThread())
                .subscribe(new MObserver<BannerBean>(null) {
                    @Override
                    protected void success(BannerBean bannerBean) {
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < bannerBean.getData().size(); i++) {
                            list.add(bannerBean.getData().get(i).getImagePath());
                        }
                        view.showBanner(bannerBean, list);
                    }

                    @Override
                    protected void error(String error) {

                    }
                });
    }

    public void articleData() {
        RetrofitManage.getInstance().articleBean()
                .compose(ApiManager.<ArticleBean>toMainThread())
                .subscribe(new MObserver<ArticleBean>(null) {
                    @Override
                    protected void success(ArticleBean articleBean) {
                        view.showArticleList(articleBean);
                    }

                    @Override
                    protected void error(String error) {

                    }
                });
    }
}
