package com.baselibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baselibrary.R;
import com.baselibrary.utils.IconfontTextView;

/**
 * date:2019/6/17
 * describe：
 */
public class MTitleBar extends RelativeLayout {
    private IconfontTextView iconfontTextView;
    private TextView titleView;

    public MTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.title_layout, this);
        titleView = view.findViewById(R.id.title);
        iconfontTextView = view.findViewById(R.id.left_back);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
        String title = typedArray.getString(R.styleable.CustomTitleBar_title);
        String leftIcon = typedArray.getString(R.styleable.CustomTitleBar_left_icon);
        int leftIconSize = typedArray.getInteger(R.styleable.CustomTitleBar_left_icon_size, 30);
        int titleSize = typedArray.getInteger(R.styleable.CustomTitleBar_title_size, 14);
        int leftIconColor = typedArray.getColor(R.styleable.CustomTitleBar_left_icon_color, Color.WHITE);
        int titleColor = typedArray.getColor(R.styleable.CustomTitleBar_title_color, Color.WHITE);
        titleView.setText(title);
        titleView.setTextColor(titleColor);
        titleView.setTextSize(titleSize);
        iconfontTextView.setText(leftIcon);
        iconfontTextView.setTextColor(leftIconColor);
        iconfontTextView.setTextSize(leftIconSize);
    }

    public void backClick(OnClickListener onClickListener) {
        iconfontTextView.setOnClickListener(onClickListener);
    }
}
