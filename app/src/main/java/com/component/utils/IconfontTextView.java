package com.component.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * date:2019/6/12
 * describe：
 */
public class IconfontTextView extends android.support.v7.widget.AppCompatTextView {
    public IconfontTextView(Context context) {
        this(context, null);
    }

    public IconfontTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconfontTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        Typeface iconfont = Typeface.createFromAsset(getResources().getAssets(), "iconfont/iconfont.ttf");
        this.setTypeface(iconfont);
    }

}