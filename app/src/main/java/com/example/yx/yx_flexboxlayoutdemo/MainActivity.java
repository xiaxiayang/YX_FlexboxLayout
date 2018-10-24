package com.example.yx.yx_flexboxlayoutdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.yx.yx_flexboxlayoutdemo.activity.CenterGridActivity;
import com.example.yx.yx_flexboxlayoutdemo.activity.LabelActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 标签布局
     * @param view
     */
    public void onLabelClick(View view) {

        startActivity(new Intent(MainActivity.this, LabelActivity.class));
    }

    /**
     * 中心展开网格布局
     * @param view
     */
    public void onCenterGridClick(View view) {
        startActivity(new Intent(MainActivity.this, CenterGridActivity.class));

    }

}
