package com.component.app.home.view;

import com.baselibrary.mvp.BaseView;
import com.component.app.home.bean.ArticleBean;
import com.component.app.home.bean.BannerBean;

import java.util.List;

/**
 * date:2019/7/31
 * describe：
 */
public interface HomeView extends BaseView {

    void showBanner(BannerBean bannerBean, List<String> list);

    void showArticleList(ArticleBean articleBean);
}
