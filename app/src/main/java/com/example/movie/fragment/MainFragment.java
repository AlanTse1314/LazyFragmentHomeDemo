package com.example.movie.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.movie.R;
import com.example.movie.activity.MainActivity;
import com.example.movie.adapter.MyViewPagerAdapter;
import com.example.movie.common.BaseFragment;
import com.example.movie.utils.DpxUtil;
import com.google.android.material.tabs.TabLayout;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainFragment extends BaseFragment implements TabLayout.OnTabSelectedListener, ViewSwitcher.ViewFactory {

    ImageView mIvLogo;
    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.tv_search)
    SearchView mTsSearch;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;


    private List<BaseFragment> fragmentList = new ArrayList<>();
    public String[] strings = {"首页", "电影", "剧集", "综艺", "动漫"};
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
//                    textSwitcherAnimation = new TextSwitcherAnimation(mTsSearch, searchList);
//                    textSwitcherAnimation.create();
                    break;

                case 11:
                    initTabLayout();
                    break;

                default:
                    break;
            }
            return false;
        }
    });
    public MainFragment() {

    }


    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        return R.layout.fragment_main;
    }


    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 让布局中的view与fragment中的变量建立起映射
     */
    @Override
    protected void init() {

        this.mTsSearch.getLayoutParams().height =getStatusBarHeight(baseActivity) + DpxUtil.dpx(45);
        this.mTsSearch.setPadding(0, getStatusBarHeight(baseActivity), 0, 0);

        initFragments();
        initTabLayout();
    }

    private void initTabLayout() {
        if (!isAdded()) {
            handler.sendEmptyMessageDelayed(11, 1000);
            return;
        }
        this.mTabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) this);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getChildFragmentManager(), fragmentList);
        this.mViewPager.setAdapter(adapter);
        this.mViewPager.setOffscreenPageLimit(this.fragmentList.size());
        this.mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(this.mTabLayout));
        this.mTabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.ViewPagerOnTabSelectedListener(this.mViewPager));
        int size = this.strings.length;
        int i = 0;
        while (i < size) {
            TabLayout tabLayout = this.mTabLayout;
            tabLayout.addTab(tabLayout.newTab().setCustomView(getTabView(i)), i, i == 0);
            i++;
        }
    }

    private void initFragments() {
        this.fragmentList.clear();
//        this.fragmentList.add(new RecommendFragment());
//        int size = this.categoryList.size();
//        for (int i = 1; i < size; i++) {
//            MovieFragment allMovieFragment = new MovieFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString("link", this.categoryList.get(i).getId());
//            allMovieFragment.setArguments(bundle);
//            this.fragmentList.add(allMovieFragment);
//        }
        for (int i = 0; i < strings.length; i++) {
            FirstFragment allMovieFragment = new FirstFragment();
            Bundle bundle = new Bundle();
            bundle.putString("link", strings[i]+i);
            allMovieFragment.setArguments(bundle);
            this.fragmentList.add(allMovieFragment);
        }
    }


    private View getTabView(int i) {
//        FragmentActivity activity = getActivity();
//        if (activity == null) {
//            return null;
//        }
//        View inflate = LayoutInflater.from(activity).inflate(R.layout.layout_tab, (ViewGroup) null);
//        ((TextView) inflate.findViewById(R.id.tab_item_textview)).setText(this.strings[i]);
//        return inflate;
        Activity activity = getActivity();
        if (activity == null) {
            return null;
        }
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_tab, null);
        TextView textView = view.findViewById(R.id.tab_item_textview);
        textView.setText(strings[i]);
        return view;
    }

    /**
     * 加载要显示的数据
     */
    @Override
    protected void lazyLoad() {

    }

    @Override
    public View makeView() {
        Activity activity = getActivity();
        if (activity == null) {
            return null;
        }
        TextView tv = new TextView(activity);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        tv.setTextColor(ContextCompat.getColor(activity, R.color.color_99));
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_VERTICAL
        );
        params.leftMargin = DpxUtil.dp2px(activity, 16);
        tv.setLayoutParams(params);
        return tv;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //设置选中状态下tab字体显示样式
        View view = tab.getCustomView();
        if (view instanceof TextView) {
            ((TextView) view).setTextSize(22);
            Activity activity = getActivity();
            if (activity == null) {
                return;
            }
            ((TextView) view).setTextColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark));
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        View customView = tab.getCustomView();
        if (customView instanceof TextView) {
            TextView textView = (TextView) customView;
            textView.setTextSize(15);
            FragmentActivity activity = getActivity();
            if (activity != null) {
                textView.setTextColor(ContextCompat.getColor(activity, R.color.color_66));
            }
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}