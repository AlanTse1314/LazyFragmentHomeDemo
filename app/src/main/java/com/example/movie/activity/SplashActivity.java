package com.example.movie.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.movie.R;
import com.example.movie.common.BaseActivity;



import java.util.List;


import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;


import com.yanzhenjie.permission.runtime.Permission;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.splash_container1)
    public RelativeLayout container1;

    @BindView(R.id.iv_splash)
    public ImageView ivSplash;

    @BindView(R.id.tv_skip_view)
    public TextView tvSkipView;

    @BindView(R.id.tv_version)
    public TextView tvVersion;
    private CountDownTimer timer;


    private String[] needPermissions = {Permission.READ_PHONE_STATE, Permission.WRITE_EXTERNAL_STORAGE,Permission.READ_EXTERNAL_STORAGE};

    @Override
    public int getContentViewResId() {
        return R.layout.activity_splash;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //        设置状态栏全透明
//        StatusBarUtil.setTransparent(baseActivity);

        requestPermission(needPermissions);
    }

    /**
     * Request permissions.
     */
    @SuppressLint("WrongConstant")
    private void requestPermission(String... permissions) {
        AndPermission.with(this)
                .runtime()
                .permission(permissions)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        initData();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(
                                SplashActivity.this, permissions)) {
                            showSettingDialog(SplashActivity.this, permissions);
                        } else {
                            requestPermission(needPermissions);
                        }
                    }
                })
                .start();
    }

    private void initData() {
        ImageView imageView = new ImageView(SplashActivity.this);
        //        ----每日bing：https://api.isoyu.com/bing_images.php
//        ----美女图片壁纸：https://api.isoyu.com/mm_images.php
//        ----网红专栏壁纸：https://api.isoyu.com/beibei_images.php
//————————————————
//        版权声明：本文为CSDN博主「庭户皓己盈」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/SectSnow/article/details/115835711

        Glide.with(imageView.getContext()).load("https://api.isoyu.com/mm_images.php").into(imageView);
        this.container1.addView(imageView);
        this.tvSkipView.setVisibility(View.VISIBLE);
        PackageManager manager = getPackageManager();
        String name = "null";

        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        tvVersion.setText(info.versionName+":"+info.versionCode);

        this.ivSplash.setVisibility(View.VISIBLE);
        this.ivSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "ivSplash.setOnClickListener", Toast.LENGTH_SHORT).show();
            }
        });

        this.tvSkipView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        });
        // 判断当前Activity是否isFinishing()，
        // 避免在finish，所有对象都为null的状态下执行CountDown造成内存泄漏
        if (!isFinishing()) {
            timer = new CountDownTimer(1000 * 6, 1000) {
                @SuppressLint("SetTextI18n")
                @Override
                public void onTick(long millisUntilFinished) {
                    // TODO: 耗时操作，如异步登录
                    // ......
                    int time = (int) millisUntilFinished;
                    tvSkipView.setText(time / 1000 + " 跳过");

                }

                @Override
                public void onFinish() {
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }
            }.start();
        }


    }



    /**
     * Display setting dialog.
     */
    public void showSettingDialog(Context context, final List<String> permissions) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        String message = context.getString(R.string.message_permission_always_failed,
                TextUtils.join("\n", permissionNames));
        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle(R.string.title_dialog)
                .setMessage(message)
                .setPositiveButton(R.string.setting, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setPermission();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermission(needPermissions);
                    }
                })
                .show();
    }

    /**
     * Set permissions.
     */
    @SuppressLint("WrongConstant")
    private void setPermission() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .onGranted(permissions -> {
                    // Storage permission are allowed.
                })
                .onDenied(permissions -> {
                    // Storage permission are not allowed.
                })
                .start();
//        AndPermission.with(this)
//                .runtime()
//                .setting()
//                .onComeback(new Setting.Action() {
//                    @Override
//                    public void onAction() {
//                        requestPermission(needPermissions);
//                    }
//                })
//                .start();
    }

}