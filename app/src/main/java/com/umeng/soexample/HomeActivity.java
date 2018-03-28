package com.umeng.soexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.umeng.soexample.analytics.UDplusActivity;
import com.umeng.soexample.analytics.UGameActivity;
import com.umeng.soexample.analytics.UappActivity;
import com.umeng.soexample.log.LogActivity;
import com.umeng.soexample.push.UpushActivity;
import com.umeng.soexample.share.UshareActivity;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getResources().getString(R.string.app_name));
        findViewById(R.id.home_update).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LogActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.home_uapp).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, UappActivity.class);
                startActivity(intent);
            }
        });
        /*findViewById(R.id.home_dplus).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, UDplusActivity.class);
                startActivity(intent);
            }
        });
        */
        findViewById(R.id.home_game).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, UGameActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.home_push).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, UpushActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.home_share).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, UshareActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }


}
