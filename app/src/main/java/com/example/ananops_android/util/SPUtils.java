package com.example.ananops_android.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * @author :Cuixiaoyang
 * @date :2019/9/23 9:00
 * @description: 操作SharePreference的工具类
 */
public class SPUtils {
    private static SPUtils instance;
    private SharedPreferences mSharedPreferences, mSafePreferences;
    public static final String FILE_NAME = "GeneralStore";
    public static final String SAFE_FILE_NAME = "SafeStore";
    private SharedPreferences.Editor mEitor, mSafeEidtor;

    public synchronized static SPUtils getInstance() {
        if (instance == null) {
            instance = new SPUtils();
        }
        return instance;
    }

    public void init(Context context) {
        if (context == null) {
            return;
        }
        mSharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        mEitor = mSharedPreferences.edit();
        mSafePreferences = context.getSharedPreferences(SAFE_FILE_NAME, Context.MODE_PRIVATE);
        mSafeEidtor = mSafePreferences.edit();
    }

    public void putString(String key, String value) {
        mEitor.putString(key, value);
        mEitor.commit();
    }

    public String getString(String key,String defValue) {
        return mSharedPreferences.getString(key,defValue);
    }

    public void putBoolean(String key, Boolean value) {
        mEitor.putBoolean(key, value);
        mEitor.commit();
    }

    public Boolean getBoolean(String key, Boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }

    public void putInt(String key, int value) {
        mEitor.putInt(key, value);
        mEitor.commit();
    }

    public int getInt(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }

}
