package com.umeng.soexample.analytics;

import android.os.Bundle;
import com.umeng.soexample.BaseActivity;
import com.umeng.soexample.R;

/**
 * Created by wangfei on 2018/1/23.
 */

public class UDplusActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("统计UDplus");
        setBackVisibily();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_uapp;
    }
}
