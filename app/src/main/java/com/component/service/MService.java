package com.component.service;


import com.baselibrary.service.BaseBean;
import com.component.app.home.bean.ArticleBean;
import com.component.app.user.bean.LoginBean;
import com.component.app.home.bean.BannerBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MService {
    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @POST("/user/login")
    Observable<BaseBean<LoginBean>> login(@Query("username") String username, @Query("password") String password);

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param repassword
     * @return
     */
    @POST("/user/register")
    Observable<BaseBean<LoginBean>> register(@Query("username") String username, @Query("password") String password, @Query("repassword") String repassword);

    /**
     * 退出
     *
     * @return
     */
    @GET("/user/logout/json")
    Observable<BaseBean> logout();

    /**
     * 轮播图
     *
     * @return
     */
    @GET("/banner/json")
    Observable<BannerBean> bannerData();

    /**
     * 首页文章列表
     *
     * @return
     */
    @GET("article/list/0/json")
    Observable<ArticleBean> articleBean();
}
