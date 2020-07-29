package com.farmmanagement.utils;

import android.content.Context;
import android.graphics.Typeface;

public class FontUtils {

    public static Typeface getFontTypeBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/nunito_bold.ttf");
    }

    public static Typeface getFontTypeRegular(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/nunito.ttf");
    }
}
