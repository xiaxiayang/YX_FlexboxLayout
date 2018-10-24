package com.example.yx.yx_flexboxlayoutdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by pc on 2018/10/9.
 */

public  abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        bindEvent();
        initData();
    }


    protected abstract  void initView();
    protected abstract void bindEvent();
    protected abstract void initData();
}
