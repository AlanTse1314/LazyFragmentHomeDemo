package com.example.movie.activity;

import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.movie.R;
import com.example.movie.common.BaseActivity;
import com.example.movie.adapter.MyViewPagerAdapter;
import com.example.movie.common.BaseFragment;
import com.example.movie.fragment.LiveFrameFragment;
import com.example.movie.fragment.MainFragment;
import com.example.movie.fragment.MeFragment;
import com.example.movie.fragment.NewestFrameFragment;
import com.example.movie.fragment.ResourceFrameFragment;
import com.example.movie.utils.DpxUtil;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener{
    public MyViewPagerAdapter pagerAdapter;

    @BindView(R.id.view_pager_main)
    public ViewPager mViewPager;

    @BindView(R.id.rg_bottom)
    public RadioGroup radioGroup;

    @BindView(R.id.rb_home)
    public RadioButton rbHome;

    @BindView(R.id.rb_live)
    public RadioButton rbLive;

    @BindView(R.id.rb_magnet)
    public RadioButton rbMagnet;

    @BindView(R.id.rb_me)
    public RadioButton rbMe;

    @BindView(R.id.rb_newest)
    public RadioButton rbNewest;

    public List<BaseFragment> listFragment = new ArrayList<>();

    public int index = 0;

    /**
     * 获取布局
     *
     * @return layoutId
     */
    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    /**
     * 初始化
     *
     * @param savedInstanceState Bundle
     */
    @Override
    public void init(Bundle savedInstanceState) {
        Drawable drawable = getResources().getDrawable(R.drawable.rbtn_main_home_selector);
        drawable.setBounds(0, 0, DpxUtil.dpx(20), DpxUtil.dpx(20));
        this.rbHome.setCompoundDrawables(null, drawable, null, null);
        Drawable drawable2 = getResources().getDrawable(R.drawable.rbtn_main_newest_selector);
        drawable2.setBounds(0, 0, DpxUtil.dpx(21), DpxUtil.dpx(21));
        this.rbNewest.setCompoundDrawables(null, drawable2, null, null);
        Drawable drawable3 = getResources().getDrawable(R.drawable.rbtn_main_live_selector);
        drawable3.setBounds(0, 0, DpxUtil.dpx(21), DpxUtil.dpx(21));
        this.rbLive.setCompoundDrawables(null, drawable3, null, null);
        Drawable drawable4 = getResources().getDrawable(R.drawable.rbtn_main_magnet_selector);
        drawable4.setBounds(0, 0, DpxUtil.dpx(20), DpxUtil.dpx(20));
        this.rbMagnet.setCompoundDrawables(null, drawable4, null, null);
        Drawable drawable5 = getResources().getDrawable(R.drawable.rbtn_main_me_selector);
        drawable5.setBounds(0, 0, DpxUtil.dpx(20), DpxUtil.dpx(20));
        this.rbMe.setCompoundDrawables(null, drawable5, null, null);

        MainFragment mainFragment = new MainFragment();
        NewestFrameFragment newestFrameFragment = new NewestFrameFragment();
        LiveFrameFragment liveFrameFragment = new LiveFrameFragment();
        ResourceFrameFragment resourceFrameFragment = new ResourceFrameFragment();
        MeFragment meFragment = new MeFragment();

        this.listFragment.add( mainFragment);
        this.listFragment.add( newestFrameFragment);
        this.listFragment.add( liveFrameFragment);
        this.listFragment.add( resourceFrameFragment);
        this.listFragment.add( meFragment);
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(fragmentManager, this.listFragment);
        this.pagerAdapter = myViewPagerAdapter;
        this.mViewPager.setAdapter(myViewPagerAdapter);
        this.mViewPager.setOffscreenPageLimit(listFragment.size());
        this.mViewPager.addOnPageChangeListener(this);
        this.radioGroup.setOnCheckedChangeListener(this);
        this.radioGroup.check(R.id.rb_home);

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home :
                index = 0;
                break;
            case R.id.rb_live :
                index = 2;
                break;
            case R.id.rb_magnet :
                index = 3;
                break;
            case R.id.rb_me :
                index = 4;
                break;
            case R.id.rb_newest :
                index = 1;
                break;
        }
        mViewPager.setCurrentItem(index, false);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.e("position", String.valueOf(position));
//        Log.e("rb_home", String.valueOf(R.id.rb_home));
        switch (position) {
            case 0 :
                rbHome.setChecked(true);
                break;
            case 1 :
                rbNewest.setChecked(true);
                break;
            case 2 :
                rbLive.setChecked(true);
                break;
            case 3 :
                rbMagnet.setChecked(true);
                break;
            case 4 :
                rbMe.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}