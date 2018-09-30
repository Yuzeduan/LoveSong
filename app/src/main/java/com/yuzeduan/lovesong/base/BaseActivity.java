package com.yuzeduan.lovesong.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yuzeduan.lovesong.music.event.MusicConditionEvent;

import de.greenrobot.event.EventBus;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化变量
        initVariables();
        // 初始化布局
        initView(savedInstanceState);
    }


    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().postSticky(getMusicCondition());
    }

    // 获取当前播放栏的状态,传递给下个活动
    protected abstract MusicConditionEvent getMusicCondition();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initVariables();

}
