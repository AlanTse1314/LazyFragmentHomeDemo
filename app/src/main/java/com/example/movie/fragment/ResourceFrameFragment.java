package com.example.movie.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.movie.R;
import com.example.movie.common.BaseFragment;


public class ResourceFrameFragment extends BaseFragment {

    public ResourceFrameFragment() {
    }

    public static ResourceFrameFragment newInstance(String param1, String param2) {
        ResourceFrameFragment fragment = new ResourceFrameFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }


    /**
     * 加载页面布局文件
     *
     * @return 布局ID
     */
    @Override
    protected int setContentView() {
        return R.layout.fragment_resource_frame;
    }

    /**
     * 让布局中的view与fragment中的变量建立起映射
     */
    @Override
    protected void init() {

    }

    /**
     * 加载要显示的数据
     */
    @Override
    protected void lazyLoad() {
        Toast.makeText(baseActivity, "ResourceFrameFragment", Toast.LENGTH_SHORT).show();
    }
}