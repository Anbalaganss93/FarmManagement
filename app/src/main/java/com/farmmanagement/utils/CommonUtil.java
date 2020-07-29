package com.farmmanagement.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class CommonUtil {

    public static String mobilenumber;

    public static void closeKeyPad(Context context,View view) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
