package com.example.movie.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



import java.util.Objects;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    protected final String TAG = getClass().getSimpleName();

    protected View rootView;
    private boolean isInitData = false;
    public boolean isVisible = false;
    protected boolean isPrepareView = false;
    public BaseActivity baseActivity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(setContentView(), container, false);
            ButterKnife.bind(this, rootView);
            init();
        }
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        baseActivity = (BaseActivity)context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepareView=true;//此时view已经加载完成，设置其为true
    }

    /**
     * fragment生命周期中onViewCreated之后的方法 在这里调用一次懒加载 避免第一次可见不加载数据
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isCanLoadData();//懒加载
    }


    @Override
    public void onResume() {
        super.onResume();
            isVisible = true;
            isCanLoadData();
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            isVisible = true;
//            isCanLoadData();
//        } else {
//            isVisible = false;
//        }
//    }

    private void isCanLoadData() {
        //所以条件是view初始化完成并且对用户可见
        if (!isInitData && isVisible && isPrepareView) {
            lazyLoad();
            //防止重复加载数据
            isInitData = true;
        }
    }

    /**
     * 加载页面布局文件
     *
     * @return 布局ID
     */
    protected abstract int setContentView();

    /**
     * 让布局中的view与fragment中的变量建立起映射
     */
    protected abstract void init();

    /**
     * 加载要显示的数据
     */
    protected abstract void lazyLoad();

    /**
     * 页面跳转
     *
     * @param clazz 目标页面
     */
    protected void startNoIntent(Class clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    /**
     * 页面跳转(带Intent)
     *
     * @param clazz 目标页面
     */
    protected void startWithIntent(Class clazz, Intent intent) {
        intent.setClass(Objects.requireNonNull(getActivity()), clazz);
        startActivity(intent);
    }

    /**
     * 打印
     *
     * @param msg String
     */
    protected void logI(String msg) {
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
}

