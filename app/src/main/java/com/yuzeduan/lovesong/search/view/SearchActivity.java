package com.yuzeduan.lovesong.search.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.BaseActivity;
import com.yuzeduan.lovesong.search.event.SearchMessageEvent;
import com.yuzeduan.lovesong.util.DensityUtil;

import de.greenrobot.event.EventBus;

/**
 * 搜索的主界面
 */
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
        replaceFragment(new HotWordFragment());
        initClick();
        setSearchView();
    }

    private void setSearchView() {
        final EditText editText = mSearchView.findViewById(R.id.search_src_text);
        editText.setTextSize(DensityUtil.dpToPx(5));
        editText.setTextColor(getResources().getColor(R.color.white));
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
        replaceFragment(new SearchMainFragment());
        EventBus.getDefault().postSticky(new SearchMessageEvent(s));
        Log.d("SearchActivity", "onQueryTextSubmit: "+s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        Log.d("SearchActivity", "onQueryTextChange: ");
        if(s.equals("")) {
            replaceFragment(new HotWordFragment());
        }
        return false;
    }

    /**
     * 用来将FrameLayout换成Fragment
     * @param fragment
     */
    private void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.below_flt, fragment);
        transaction.commit();
    }
}
