package com.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/*
 * 文件名:    SharePreUtils
 * 创建者:    ZSY
 * 创建时间:  2016/12/12 on 11:58
 * 描述:     SharedPreferences存取工具类
 */

public class SharePreConfigUtils {

    //配置文件，文件名
    public static final String SHARE_NAME = "config_info";

    /**
     * 万能put
     *
     * @param mContext
     * @param key
     * @param values
     */
    public static void put(Context mContext, String key, Object values) {
        if (values instanceof String) {
            putString(mContext, key, (String) values);
        } else if (values instanceof Integer) {
            putInt(mContext, key, (Integer) values);
        } else if (values instanceof Boolean) {
            putBoolean(mContext, key, (Boolean) values);
        } else {
            //出去上面三个类型，都存储为String
            putString(mContext, key, String.valueOf(values));
        }

    }

    /**
     * 存字符串
     *
     * @param mContext 上下文
     * @param key      键
     * @param values   值
     */
    public static void putString(Context mContext, String key, String values) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, values).commit();
    }


    /**
     * 取字符串
     *
     * @param mContext 上下文
     * @param key      键
     * @param values   默认值
     * @return 取出的值
     */
    public static String getString(Context mContext, String key, String values) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, values);
    }


    /**
     * 存布尔值
     *
     * @param mContext 上下文
     * @param key      键
     * @param values   值
     */
    public static void putBoolean(Context mContext, String key, boolean values) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, values).commit();
    }

    /**
     * 取布尔值
     *
     * @param mContext 上下文
     * @param key      键
     * @param values   默认值
     * @return true/false
     */
    public static boolean getBoolean(Context mContext, String key, boolean values) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, values);
    }

    /**
     * 存int值
     *
     * @param mContext 上下文
     * @param key      键
     * @param values   值
     */
    public static void putInt(Context mContext, String key, int values) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, values).commit();
    }

    /**
     * 存HashSet值
     *
     * @param mContext 上下文
     * @param key      键
     * @param hashSet  值
     */
    public static void putHashSet(Context mContext, String key, HashSet hashSet) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        sp.edit().putStringSet(key, hashSet).commit();
    }

    /**
     * 取HashSet值
     *
     * @param mContext 上下文
     * @param key      键
     * @param key      默认值
     * @return
     */
    public static Set getHashSet(Context mContext, String key) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        return sp.getStringSet(key, new HashSet());
    }

    /**
     * 取int值
     *
     * @param mContext 上下文
     * @param key      键
     * @param values   默认值
     * @return
     */
    public static int getInt(Context mContext, String key, int values) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, values);
    }

    /**
     * 删除一条字段
     *
     * @param mContext 上下文
     * @param key      键
     */
    public static void deleShare(Context mContext, String key) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        //单个清理
        sp.edit().remove(key).commit();
    }

    /**
     * 删除全部数据
     *
     * @param mContext 上下文
     */
    public static void deleShareAll(Context mContext) {
        SharedPreferences sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
        //全部清理
        sp.edit().clear().commit();
    }

    /**
     * 查看SharedPreferences的内容
     */
    public static String lookSharePre(Context context) {
        try {
            FileInputStream stream = new FileInputStream(new File("/data/data/" +
                    context.getPackageName() + "/shared_prefs", SHARE_NAME + ".xml"));
            BufferedReader bff = new BufferedReader(new InputStreamReader(stream));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = bff.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "未找到当前配置文件！";
    }


    /**
     * 将bean对象的字段和值添加到sp中
     *
     * @param object
     */
    public static void addSp(Context context, Object object) {
        if (object == null) {
            return;
        }
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            String name = field.getName();
            put(context, name, value);
        }
    }
}