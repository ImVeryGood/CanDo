package com.component.net;

import com.baselibrary.service.ApiManager;
import com.component.service.MService;

/**
 * date:2019/5/27
 * describe：
 */
public class RetrofitManage {
    private static String BASE_URL = "https://www.wanandroid.com";

    public static MService getInstance() {
        return ApiManager.getInstance().setService(BASE_URL, MService.class);
    }
}
