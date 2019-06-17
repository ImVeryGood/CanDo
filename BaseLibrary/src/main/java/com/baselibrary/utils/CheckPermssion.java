package com.baselibrary.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.PermissionChecker;

public class CheckPermssion {
    /**
     * 检测permission是否存在
     *
     * @param context
     * @param permission 要检测的权限
     * @return
     */
    public static boolean selfPermissionGranted(Context context, String permission) {
        boolean ret = true;
        if (Build.VERSION.SDK_INT >= 23) {
            ret = context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        } else {
            ret = PermissionChecker.checkSelfPermission(context, permission) == PermissionChecker.PERMISSION_GRANTED;
        }
        return ret;
    }
}
