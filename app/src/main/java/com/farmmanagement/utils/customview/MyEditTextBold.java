package com.farmmanagement.utils.customview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import com.farmmanagement.utils.FontUtils;



public class MyEditTextBold extends AppCompatEditText {

    public MyEditTextBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyEditTextBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditTextBold(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            setTypeface(FontUtils.getFontTypeBold(getContext()));
        }
    }

}