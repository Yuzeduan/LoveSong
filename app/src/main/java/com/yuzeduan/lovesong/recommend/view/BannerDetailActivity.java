package com.yuzeduan.lovesong.recommend.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.BaseActivity;
import com.yuzeduan.lovesong.music.event.MusicConditionEvent;

public class BannerDetailActivity extends BaseActivity {
    private Toolbar mToolbar;
    private String mPath;
    private WebView mWebView;

    public static void actionStart(Context context, String path){
        Intent intent = new Intent(context, BannerDetailActivity.class);
        intent.putExtra("path", path);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
        mPath = getIntent().getStringExtra("path");
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_banner_detail);
        mToolbar = findViewById(R.id.toolbar);
        initToolbar();
        mWebView = findViewById(R.id.banner_detail_wv);
        mWebView.loadUrl(mPath);
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected MusicConditionEvent getMusicCondition() {
        return null;
    }
}
