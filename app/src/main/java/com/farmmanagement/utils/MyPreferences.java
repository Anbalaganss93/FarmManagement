package com.farmmanagement.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Prabhu on 15-01-2016.
 */
public class MyPreferences {
    static SharedPreferences sharedPreferences;
    private Context context;

    public MyPreferences(Context context) {
//        this.context = BaseApplication.baseApplication;
        sharedPreferences = context.getSharedPreferences("main", Context.MODE_PRIVATE);
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    public String getString(String key, String value) {
        return sharedPreferences.getString(key, value);
    }

    public String setString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
        return key;
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public void setInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void setLong(String key, long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public void removeAll() {
        SharedPreferences.Editor editor = sharedPreferences.edit().clear();
        editor.apply();
    }

    public boolean contains(String key) {
        if (sharedPreferences.contains(key)) {
            return true;
        }
        return false;
    }
}
