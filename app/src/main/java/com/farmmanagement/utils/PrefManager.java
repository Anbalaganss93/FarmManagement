package com.farmmanagement.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private static final String PREF_NAME = "POND_FARM";
    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;
    private int PRIVATE_MODE = 0;
    private static final String KEY_MOBILE_NUMBER = "login_mobilenumber";

    public PrefManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static String getLoggedInMobileNumber() {
        return pref.getString(KEY_MOBILE_NUMBER, "");

    }

    public static void setLoggedInMobileNumber(String mobilenumber) {
        editor.putString(KEY_MOBILE_NUMBER, mobilenumber);
        editor.commit();
    }

}
