package com.example.movie.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movie.R;
import com.example.movie.common.BaseFragment;


public class FirstFragment extends BaseFragment {
    TextView tv;
    private String mParam1;


    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString("link", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("link");
        }
    }


    @Override
    protected int setContentView() {
        return R.layout.fragment_first;
    }

    @Override
    protected void init() {
        tv =rootView.findViewById(R.id.tv);
    }

    @Override
    protected void lazyLoad() {
        tv.setText(mParam1);
        Toast.makeText(baseActivity, "FirstFragment"+mParam1, Toast.LENGTH_SHORT).show();
    }
}