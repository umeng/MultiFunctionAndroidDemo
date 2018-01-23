package com.umeng.soexample.share;

import android.os.Bundle;
import com.umeng.soexample.BaseActivity;
import com.umeng.soexample.R;

/**
 * Created by wangfei on 2018/1/23.
 */

public class UshareActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("分享");
        setBackVisibily();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_upush;
    }
}
