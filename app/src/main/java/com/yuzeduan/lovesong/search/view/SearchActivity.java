package com.yuzeduan.lovesong.search.view;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageButton;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.BaseActivity;

public class SearchActivity extends BaseActivity implements SearchView.OnQueryTextListener{
    private ImageButton mImageButton;
    private SearchView mSearchView;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);
        mImageButton = findViewById(R.id.back_ibn);
        mSearchView = findViewById(R.id.search_sv);
        mSearchView.setOnQueryTextListener(this);
        initClick();
    }

    private void initClick() {
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
