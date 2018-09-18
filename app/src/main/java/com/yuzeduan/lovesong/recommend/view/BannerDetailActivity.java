package com.yuzeduan.lovesong.recommend.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.BaseActivity;

public class BannerDetailActivity extends BaseActivity {
    private Toolbar mToolbar;

    @Override
    protected void initVariables() {
        setContentView(R.layout.activity_banner_detail);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mToolbar = findViewById(R.id.toolbar);
        initToolbar();
    }

    @Override
    protected void loadData() {
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
}
