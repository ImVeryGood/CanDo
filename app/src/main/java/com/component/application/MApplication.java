package com.component.application;

import android.app.Application;
import android.content.Context;

import com.baselibrary.skin.SkinManager;

/**
 * date:2019/5/23
 * describe：
 */
public class MApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
        mContext = this;
    }

    public static Context getInstance() {
        return mContext;
    }
}
