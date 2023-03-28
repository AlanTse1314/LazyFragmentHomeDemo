package com.example.movie.adapter;




import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.movie.common.BaseFragment;

import java.util.List;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> list;

    public MyViewPagerAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }
}
