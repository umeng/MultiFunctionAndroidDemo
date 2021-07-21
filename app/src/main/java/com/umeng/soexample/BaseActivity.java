package com.umeng.soexample;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

/**
 * Created by wangfei on 2018/1/23.
 */

public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.titlebg));

        }
        setContentView(getLayout());
        setTitle(getResources().getString(R.string.app_name));

        //推送平台多维度推送决策必须调用方法(需要同意隐私协议之后初始化完成调用)
        PushAgent.getInstance(this).onAppStart();


    }
    public abstract int getLayout();
    public void setTitle(String title){
        ((TextView)findViewById(R.id.umeng_title)).setText(title);
    }
    public void setBackVisibily(){
        findViewById(R.id.umeng_back).setVisibility(View.VISIBLE);
        findViewById(R.id.umeng_back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                clickBack(view);
            }
        });
    }
    public void clickBack(View view){
        finish();
    }

}
