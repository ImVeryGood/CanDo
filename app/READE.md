### TextInputLayout

**更换选中悬浮字的颜色以及大小**

    <style name="Widget.Design.TextInputLayout" parent="AppTheme">
        <item name="hintTextAppearance">@style/FloatingStyle</item>
    </style>
    <!--设置悬浮字的颜色以及字号-->
    <style name="FloatingStyle" parent="TextAppearance.Design.Hint">
        <item name="android:textColor">@color/themeColor</item>
        <item name="android:textSize">12sp</item>
    </style>
    <!--设置错误提示的theme-->
    <style name="AppTheme.TextErrorAppearance" parent="TextAppearance.Design.Error">
        <!-- Error message appearance here -->
        <item name="android:textColor">#ff0000</item>
        <item name="android:textSize">20sp</item>
    </style>

**修改InputText 下划线颜色：**

    <style name="AppTheme" parent="Theme.AppCompat.Light">
        <!-- Customize your theme here. -->
        <item name="colorControlNormal">@color/grayCc</item>
        <item name="colorControlActivated">@color/themeColor</item>
        <item name="colorAccent">@color/themeColor</item>
    </style>

**最后在Xml设置TextInputEditText的theme**

    <android.support.design.widget.TextInputEditText
                android:id="@+id/pass_word"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:hint="@string/password"
                android:textSize="15sp"
                android:inputType="textPassword"
                android:singleLine="true"
                android:theme="@style/AppTheme" />


###InconFont

1.首先要先支持矢量图：

    defaultConfig {
       ...
        vectorDrawables.useSupportLibrary = true
        
    }

2.在IconFont下载自己的素材

在项目main下新建inconfont文件夹，把下载的已.ttf结尾的文件拷贝过来!
[](https://i.imgur.com/0jhKkzv.png)

3.在strings.xml中

    <resources>
     .....
    <string name="icon_eye_see">&#xe607;</string>
    <string name="icon_eye_close">&#xe606;</string>
    <string name="google_phone">&#xe601;</string>
    </resources>

4.自定义IconfontTextView用来承载iconFont，避免每次都需要设置TypeFace
    
    package com.component.utils;

    import android.content.Context;
    import android.graphics.Typeface;
    import android.support.annotation.Nullable;
    import android.util.AttributeSet;
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

**使用：**

    <com.component.utils.IconfontTextView
            android:id="@+id/eye_password_confirm"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentRight="true"
            android:layout_centerVertical="true"
            android:gravity="center"
            android:paddingRight="@dimen/dp_10"
            android:text="@string/icon_eye_close"
            android:textColor="@color/themeColor"
            android:textSize="@dimen/dp_20" />

