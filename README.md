# ToolbarTest
Toolbar详解，系统自带Toolbar和自定义Toolbar
# Toolbar简介

Toolbar 是 Android 5.0 推出的一个 Material Design 风格的导航控件 ,用来取代之前的 Actionbar 。与 Actionbar 相比，Toolbar 明显要灵活的多。它不像 Actionbar 一样，一定要固定在Activity的顶部，而是可以放到界面的任意位置，看下官方文档介绍：

![image](http://upload-images.jianshu.io/upload_images/1953022-43cf17ed2a59ee13.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
注意看着几部分： - 1.设置导航栏图标； - 2.设置App的logo； - 3.支持设置标题和子标题； - 4.支持添加一个或多个的自定义控件； - 5.支持Action Menu；

# 一.Toolbar基本使用

1.Toolbar基本使用效果图

![image](http://upload-images.jianshu.io/upload_images/1953022-4d37bd6e5760af4b.gif?imageMogr2/auto-orient/strip)

这个效果图分别对应上面的那五部分： 1.左侧返回箭头按钮是导航栏图标； 2.小绿人是logo； 3.Title是标题，Subtitle是子标题； 4.自定义控件，是一个TextView 5.Menu
# 2.使用

首先，在布局文件 activity_normal_toolbar.xml.xml 中添加进 Toolbar 控件

```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.study.toolbardemo.NormalToolbarActivity">
    <android.support.v7.widget.Toolbar
        android:id="@+id/toolbar_normal"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:title="Title"
        app:titleTextColor="#ffffff"
        android:background="@color/colorPrimaryDark"
        app:subtitle="SubTitle"
        app:subtitleTextColor="#ffffff"
        app:logo="@mipmap/ic_launcher"
        app:navigationIcon="@mipmap/icon_back_32px">
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textColor="#ff0000"
            android:text="text"/>
    </android.support.v7.widget.Toolbar>
</RelativeLayout>
```
然后新建一个menu文件夹，创建一个menu对应的xml文件
```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto">
    <item
        android:id="@+id/action_search"
        app:showAsAction="ifRoom"
        android:icon="@mipmap/icon_search"
        android:title="@string/menu_search"/>

    <item
        android:id="@+id/action_notification"
        app:showAsAction="ifRoom"
        android:icon="@mipmap/icon_notification"
        android:title="@string/menu_notification"/>

    <item
        android:id="@+id/action_item_one"
        app:showAsAction="never"
        android:title="@string/item_one"/>

    <item
        android:id="@+id/action_item_two"
        app:showAsAction="never"
        android:title="@string/item_two"/>
</menu>
```
然后在Activity中设置几个属性即可，因为我们大部分设置都在xml中设置了，也可以动态设置

```
package org.yh.toolbartest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class NormalToolbarActivity extends AppCompatActivity
{

    public Toolbar mNormalToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_toolbar);
        StatusBarCompat.compat(this, 0xFF303F9F);
        initToolbar();
    }

    private void initToolbar()
    {
        mNormalToolbar= (Toolbar) findViewById(R.id.toolbar_normal);
        //设置menu
        mNormalToolbar.inflateMenu(R.menu.menu);
        //设置menu的点击事件
        mNormalToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {

                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_search)
                {
                    Toast.makeText(NormalToolbarActivity.this, R.string.menu_search, Toast
                            .LENGTH_SHORT).show();

                }
                else if (menuItemId == R.id.action_notification)
                {
                    Toast.makeText(NormalToolbarActivity.this, R.string.menu_notification, Toast
                            .LENGTH_SHORT).show();

                }
                else if (menuItemId == R.id.action_item_one)
                {
                    Toast.makeText(NormalToolbarActivity.this, R.string.item_one, Toast
                            .LENGTH_SHORT).show();

                }
                else if (menuItemId == R.id.action_item_two)
                {
                    Toast.makeText(NormalToolbarActivity.this, R.string.item_two, Toast
                            .LENGTH_SHORT).show();

                }
                return true;
            }
        });
        //设置左侧NavigationIcon点击事件
        mNormalToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(NormalToolbarActivity.this, "点击了左侧按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

```
# 3.注意问题
在xml中设置时要注意，命名空间不能是android开头，可以是app也可以是其他的

```
        app:title="Title"
        app:titleTextColor="#ffffff"
        app:subtitle="SubTitle"
        app:subtitleTextColor="#ffffff"
        app:logo="@mipmap/ic_launcher"
        app:navigationIcon="@mipmap/icon_back_32px"
```
要设置主题，隐藏原来自带的，我是直接在style中设置了

```
  <!--
    //要设置主题，隐藏原来自带的，我是直接在style中设置了
    //Theme.AppCompat.Light.NoActionBar
    //或者代码设置
    //如果你不想改主题，可以在BaseActivity中添加这段代码，在onCreate中，setContentView之前
    //supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
    //如果是继承Activity就应该调用requestWindowFeature(Window.FEATURE_NO_TITLE)）；
    colorPrimary 对应标题栏，也就是toolbar的颜色
    colorPrimaryDark对应状态栏的颜色
    colorAccent 对应一些控件，像输入框编辑，RadioButton选中、CheckBox等选中时的颜色。
    -->
    <!-- Base application theme. -->
    <style name="BaseAppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <!-- Customize your theme here. -->
        <!--<item name="colorPrimary">@color/colorPrimary</item>-->
        <!--<item name="colorPrimaryDark">@color/colorPrimaryDark</item>-->
        <!--<item name="colorAccent">@color/colorAccent</item>-->

        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/primary</item>
        <item name="colorPrimaryDark">@color/primary_dark</item>
        <item name="colorAccent">#FF4081</item>
    </style>

    <!-- Base application theme. -->
    <style name="AppTheme" parent="@style/BaseAppTheme">
    </style>
```
如果你不想改主题，可以在BaseActivity中添加这段代码，在onCreate中，setContentView之前 supportRequestWindowFeature(Window.FEATURE_NO_TITLE) 如果是继承Activity就应该调用requestWindowFeature(Window.FEATURE_NO_TITLE)）；

# 二.自定义Toolbar

## 1.自定义Toolbar效果图
![image](http://upload-images.jianshu.io/upload_images/1953022-6f39b5f517ec5697.gif?imageMogr2/auto-orient/strip)
## 2.实现

这里我只是简单的自定义了一下，起到一个抛砖引玉的作用吧。

贴出代码，有注释很详细的，基本上就是把Toolbar当成一个容器，往里面填充个布局

```
package org.yh.toolbartest;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yhlyl on 2017/5/11.
 */

public class SimpleToolbar extends Toolbar
{

    /**
     * 左侧Title
     */
    private TextView mTxtLeftTitle;
    /**
     * 中间Title
     */
    private TextView mTxtMiddleTitle;
    /**
     * 右侧Title
     */
    private TextView mTxtRightTitle;

    public SimpleToolbar(Context context)
    {
        super(context);
    }

    public SimpleToolbar(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public SimpleToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        mTxtLeftTitle = (TextView) findViewById(R.id.txt_left_title);
        mTxtMiddleTitle = (TextView) findViewById(R.id.txt_main_title);
        mTxtRightTitle = (TextView) findViewById(R.id.txt_right_title);
    }

    //设置中间title的内容
    public void setMainTitle(String text)
    {
        this.setTitle(" ");
        mTxtMiddleTitle.setVisibility(View.VISIBLE);
        mTxtMiddleTitle.setText(text);
    }

    //设置中间title的内容文字的颜色
    public void setMainTitleColor(int color)
    {
        mTxtMiddleTitle.setTextColor(color);
    }

    //设置title左边文字
    public void setLeftTitleText(String text)
    {
        mTxtLeftTitle.setVisibility(View.VISIBLE);
        mTxtLeftTitle.setText(text);
    }

    //设置title左边文字颜色
    public void setLeftTitleColor(int color)
    {
        mTxtLeftTitle.setTextColor(color);
    }

    //设置title左边图标
    public void setLeftTitleDrawable(int res)
    {
        Drawable dwLeft = ContextCompat.getDrawable(getContext(), res);
        dwLeft.setBounds(0, 0, dwLeft.getMinimumWidth(), dwLeft.getMinimumHeight());
        mTxtLeftTitle.setCompoundDrawables(dwLeft, null, null, null);
    }

    //设置title左边点击事件
    public void setLeftTitleClickListener(OnClickListener onClickListener)
    {
        mTxtLeftTitle.setOnClickListener(onClickListener);
    }

    //设置title右边文字
    public void setRightTitleText(String text)
    {
        mTxtRightTitle.setVisibility(View.VISIBLE);
        mTxtRightTitle.setText(text);
    }

    //设置title右边文字颜色
    public void setRightTitleColor(int color)
    {
        mTxtRightTitle.setTextColor(color);
    }

    //设置title右边图标
    public void setRightTitleDrawable(int res)
    {
        Drawable dwRight = ContextCompat.getDrawable(getContext(), res);
        dwRight.setBounds(0, 0, dwRight.getMinimumWidth(), dwRight.getMinimumHeight());
        mTxtRightTitle.setCompoundDrawables(null, null, dwRight, null);
    }

    //设置title右边点击事件
    public void setRightTitleClickListener(OnClickListener onClickListener)
    {
        mTxtRightTitle.setOnClickListener(onClickListener);
    }

}

```
贴下xml

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:fitsSystemWindows="true"
    tools:context="org.yh.toolbartest.CustomToolbarActivity">


    <org.yh.toolbartest.SimpleToolbar
        android:id="@+id/simple_toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/colorAccent"
        android:fitsSystemWindows="true"
        android:minHeight="?attr/actionBarSize"
        app:contentInsetLeft="0dp"
        app:contentInsetStart="0dp">

        <TextView
            android:id="@+id/txt_left_title"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="10dp"
            android:drawableLeft="@mipmap/icon_back_32px"
            android:gravity="center"
            android:maxLines="1"
            android:text="返回"
            android:textColor="#ffffff"
            android:textSize="16sp"
            android:visibility="visible"/>

        <TextView
            android:id="@+id/txt_main_title"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:maxLines="1"
            android:text="标题"
            android:textColor="@android:color/white"
            android:textSize="20sp"
            android:visibility="visible"/>

        <TextView
            android:id="@+id/txt_right_title"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="right"
            android:layout_marginEnd="10dp"
            android:drawableEnd="@mipmap/icon_plus"
            android:gravity="center"
            android:textColor="#ffffff"
            android:textSize="16sp"
            android:visibility="visible"/>
    </org.yh.toolbartest.SimpleToolbar>

</LinearLayout>

```
这里面有几个地方注意下： 1.往Toolbar里面填充View的时候，外面最好不要有其他的ViewGroup，如LinearLayout，因为如果外面再包一层的话，Title就不会居中了，那样不太符合我们的设计; 2.app:contentInsetLeft=”0dp” app:contentInsetStart=”0dp”这两个是设置Toolbar左右间隔的，如果不设置的话，默认有个默认值
贴出来style
```
  <!--
    //要设置主题，隐藏原来自带的，我是直接在style中设置了
    //Theme.AppCompat.Light.NoActionBar
    //或者代码设置
    //如果你不想改主题，可以在BaseActivity中添加这段代码，在onCreate中，setContentView之前
    //supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
    //如果是继承Activity就应该调用requestWindowFeature(Window.FEATURE_NO_TITLE)）；
    colorPrimary 对应标题栏，也就是toolbar的颜色
    colorPrimaryDark对应状态栏的颜色
    colorAccent 对应一些控件，像输入框编辑，RadioButton选中、CheckBox等选中时的颜色。
    -->
    <!-- Base application theme. -->
    <style name="BaseAppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <!-- Customize your theme here. -->
        <!--<item name="colorPrimary">@color/colorPrimary</item>-->
        <!--<item name="colorPrimaryDark">@color/colorPrimaryDark</item>-->
        <!--<item name="colorAccent">@color/colorAccent</item>-->

        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/primary</item>
        <item name="colorPrimaryDark">@color/primary_dark</item>
        <item name="colorAccent">#FF4081</item>
    </style>

    <!-- Base application theme. -->
    <style name="AppTheme" parent="@style/BaseAppTheme">
    </style>
```
这里的style你可以这样写，也可以直接用NoActionBar那个主题，效果基本上一样
源码地址[：](https://github.com/android-coco/ToolbarTest)

