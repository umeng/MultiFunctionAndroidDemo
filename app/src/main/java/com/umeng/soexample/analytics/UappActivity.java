package com.umeng.soexample.analytics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgent.EScenarioType;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.soexample.BaseActivity;
import com.umeng.soexample.R;
import com.umeng.soexample.share.SharePlatformActivity;
import com.umeng.soexample.share.UshareActivity;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by wangfei on 2018/1/23.
 */

public class UappActivity extends BaseActivity {

    private Context mContext;
    private final String mPageName = "UappActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("统计UApp");
        setBackVisibily();

        mContext = this;
        // SDK在统计Fragment时，需要关闭Activity自带的页面统计，
        // 然后在每个页面中重新集成页面统计的代码(包括调用了 onResume 和 onPause 的Activity)。
        MobclickAgent.openActivityDurationTrack(false);

        // 设置为U-APP场景
        MobclickAgent.setScenarioType(mContext, EScenarioType.E_UM_NORMAL);


        findViewById(R.id.analytics_g1_b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MobclickAgent.onEvent(mContext, "click");
                MobclickAgent.onEvent(mContext, "click", "button");
                Toast.makeText(mContext, "已完成普通事件", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.analytics_g1_b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> map_ekv = new HashMap<String, String>();
                map_ekv.put("type", "popular");
                map_ekv.put("artist", "JJLin");
                MobclickAgent.onEvent(mContext, "music", map_ekv);
                Toast.makeText(mContext, "已完成多属性(K-V)事件", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.analytics_g1_b3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> map_value = new HashMap<String, String>();
                map_value.put("type", "popular");
                map_value.put("artist", "JJLin");
                MobclickAgent.onEventValue(mContext, "music", map_value, 12000);
                Toast.makeText(mContext, "已完成数值型统计", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.analytics_g2_b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MobclickAgent.onProfileSignIn("example_id");
                Toast.makeText(mContext, "已完成用户登录", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.analytics_g2_b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MobclickAgent.onProfileSignOff();
                Toast.makeText(mContext, "已完成用户登出", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.analytics_g3_b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "已完成程序崩溃", Toast.LENGTH_SHORT).show();
                "123".substring(10);
            }
        });

        findViewById(R.id.analytics_g4_b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, UFragmentStack.class));
            }
        });
        findViewById(R.id.analytics_g4_b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, UFragmentTabs.class));
            }
        });



    }

    @Override
    public int getLayout() {
        return R.layout.activity_uapp;
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(mPageName);
        //MobclickAgent.onResume(mContext); // BaseActivity中已经统一调用，此处无需再调用
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(mPageName);
        //MobclickAgent.onPause(mContext); // BaseActivity中已经统一调用，此处无需再调用
    }
}
