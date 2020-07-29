package com.farmmanagement.utils.customview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.farmmanagement.utils.FontUtils;



public class MyButtonBold extends AppCompatButton {

    public MyButtonBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyButtonBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyButtonBold(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            setTypeface(FontUtils.getFontTypeBold(getContext()));
        }
    }

}