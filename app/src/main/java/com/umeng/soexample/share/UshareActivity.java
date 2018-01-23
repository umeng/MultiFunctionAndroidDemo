package com.umeng.soexample.share;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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
        findViewById(R.id.social_share).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UshareActivity.this,SharePlatformActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.social_board).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UshareActivity.this,ShareBoardActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.social_auth).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UshareActivity.this,AuthActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.social_info).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UshareActivity.this,InfoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_ushare;
    }
}
