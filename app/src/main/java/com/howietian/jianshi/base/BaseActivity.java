package com.howietian.jianshi.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.howietian.jianshi.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
    }

    /**
     * 抽象方法，每个布局设置布局文件，需要子类去具体实现，顺便加上ButterKnife的初始化
     */
    public abstract void setMyContentView();
    private void initLayout(){
        setMyContentView();
        ButterKnife.bind(this);
        init();
    }

    public void init(){}

    /**
     * 常用的几个方法
     */
    public void showToast(String s) {
        if (!TextUtils.isEmpty(s)) {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        }
    }

    public void jumpTo(Class<?> clazz, boolean isFinish) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        if (isFinish) {
            finish();
        }
    }

    public void jumpTo(Intent intent,boolean isFinish) {
        startActivity(intent);
        if (isFinish) {
            finish();
        }
    }
}
