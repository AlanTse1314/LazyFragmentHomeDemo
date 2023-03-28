package com.example.movie.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.movie.R;


import butterknife.ButterKnife;


/**
 * @author Mita
 * @date 2018/10/9
 * @Description
 */
@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity {
    public FragmentManager fragmentManager;
    public BaseActivity baseActivity;

    protected final String TAG = getClass().getSimpleName();

//    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity=this;
        fragmentManager = getSupportFragmentManager();
        //竖屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //去除主题windowBackground
        getWindow().getDecorView().setBackground(getDrawable(R.drawable.bg_index_top_bar));
//        getWindow().getDecorView().setBackground(null);
//        设置状态栏全透明
//        StatusBarUtil.setTransparent(baseActivity);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏标题栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); //布局延伸


        //设置布局
        setContentView(getContentViewResId());
        //绑定ButterKnife
        ButterKnife.bind(this);
        //初始化状态栏
        initWhiteBar();
        //初始化
        init(savedInstanceState);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
//        AutoSize.autoConvertDensityOfGlobal(this);
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销状态栏设置
//        if (mImmersionBar != null) {
//            ImmersionBar.with(this).destroy();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 获取布局
     *
     * @return layoutId
     */
    public abstract int getContentViewResId();

    /**
     * 初始化
     *
     * @param savedInstanceState Bundle
     */
    public abstract void init(Bundle savedInstanceState);

    /**
     * 初始化状态栏（颜色White）
     */
    protected void initWhiteBar() {
//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarColor(R.color.main_color)
//                .fitsSystemWindows(true)
//                .flymeOSStatusBarFontColor(R.color.black)
//                .statusBarDarkFont(true)
//                .init();
    }

    /**
     * 初始化状态栏（颜色White）
     */
    protected void initBlackBar() {
//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarColor(R.color.black)
//                .fitsSystemWindows(true)
//                .flymeOSStatusBarFontColor(R.color.white)
//                .statusBarDarkFont(false)
//                .init();
    }

    /**
     * 初始化状态栏
     *
     * @param isTranslucent 是否透明
     */
    protected void setTranslucentBar(boolean isTranslucent) {
//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarColor(isTranslucent ? R.color.translucent : R.color.main_color)
//                .fitsSystemWindows(!isTranslucent)
//                .flymeOSStatusBarFontColor(R.color.black)
//                .statusBarDarkFont(true)
//                .init();
    }

    /**
     * 页面跳转
     *
     * @param clazz 目标页面
     */
    protected void startNoIntent(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * 页面跳转(带Intent)
     *
     * @param clazz 目标页面
     */
    protected void startWithIntent(Class clazz, Intent intent) {
        intent.setClass(this, clazz);
        startActivity(intent);
    }

    /**
     * 页面跳转(带返回)
     *
     * @param clazz 目标页面
     */
    protected void startForResult(Class clazz, Intent intent, int requestCode) {
        if (intent == null) {
            intent = new Intent();
        }
        intent.setClass(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 打印
     *
     * @param msg String
     */
    protected void log(String msg) {
        Log.i(TAG, msg);
    }

    /**
     * 打印
     *
     * @param msg String
     */
    protected void logE(String msg) {
        Log.e(TAG, msg);
    }

    /**
     * toast
     *
     * @param msg String
     */
    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * toast
     *
     * @param msgRes int
     */
    protected void showToast(int msgRes) {
        Toast.makeText(this, msgRes, Toast.LENGTH_SHORT).show();
    }

}
