package com.umeng.soexample.push;

import android.os.Bundle;
import com.umeng.soexample.BaseActivity;
import com.umeng.soexample.R;

/**
 * Created by wangfei on 2018/1/23.
 */

public class UpushActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("推送");
        setBackVisibily();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_upush;
    }
}
