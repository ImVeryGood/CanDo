package com.baselibrary.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.baselibrary.R;


/**
 * 项目名:    w5zg
 * 包  名:    com.w5kj.w5mall.utils
 * 文件名:    NotificationDialogUtils
 * 时  间:    2018/5/7 on 18:46
 * 描  述:    TODO
 *
 * @author: zjb
 */
public class NotificationDialogUtils {
    /**
     * 判断是否打开通知
     * 接受推送的通知栏
     *
     * @return
     */
    public static boolean isNotificationEnabled(final Context context) {
        if (SharePreConfigUtils.getString(context, ShareConstant.openNotification, "").equals("close")) {
            return false;
        }
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        boolean enabled = manager.areNotificationsEnabled();
        if (!enabled) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_open_notification, null);
            final AlertDialog tipNotificationDialog = new AlertDialog.Builder(context)
                    .setView(inflate)
                    .create();
            final RadioGroup radioGroup = inflate.findViewById(R.id.radio_group);
            Button btnCancel = inflate.findViewById(R.id.btn_cancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tipNotificationDialog.dismiss();
                }
            });
            Button btnConfirm = inflate.findViewById(R.id.btn_confirm);
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = radioGroup.getCheckedRadioButtonId();
                    if (i == R.id.rb_1) {//立即开启
                        goToSet(context);
                        tipNotificationDialog.dismiss();
                    } else if (i == R.id.rb_2) {//暂时关闭
                        tipNotificationDialog.dismiss();
                    } else if (i == R.id.rb_3) {//不再提醒
                        SharePreConfigUtils.put(context, ShareConstant.openNotification, "close");
                        tipNotificationDialog.dismiss();
                    }
                }
            });
            tipNotificationDialog.show();
            return false;
        } else {
            //开启
            return true;
        }
    }

    /**
     * 跳转打开通知栏设置界面
     */
    private static void goToSet(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            Intent intent = new Intent();
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", context.getPackageName());
            intent.putExtra("app_uid", context.getApplicationInfo().uid);
            context.startActivity(intent);
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
            context.startActivity(intent);
        }
    }
}
