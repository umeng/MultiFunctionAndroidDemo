package com.umeng.soexample;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View.OnClickListener;

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

        if(Build.VERSION.SDK_INT >= 23){
            String[] mPermissionList = new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE};
            this.requestPermissions(mPermissionList, 123);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

}
