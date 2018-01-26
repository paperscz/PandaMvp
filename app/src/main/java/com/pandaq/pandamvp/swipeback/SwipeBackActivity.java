package com.pandaq.pandamvp.swipeback;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

import com.pandaq.pandamvp.R;
import com.pandaq.pandamvp.modules.base.BaseActivity;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description : 可侧滑返回的 Activity
 */

public class SwipeBackActivity extends BaseActivity {

    protected SwipeBackLayout layout;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
                R.layout.swipeback_base, null);
        layout.attachToActivity(this);
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected int bindContentRes() {
        return 0;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadData() {

    }

    // 提供给子类设置 ViewPager 的接口，用于 SwipeLayout 中处理滑动冲突
    public void addViewPager(ViewPager pager) {
        layout.addViewPager(pager);
    }
}
