package com.umeng.soexample.analytics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.umeng.analytics.MobclickAgent;
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
    private final String mPageName = "AnalyticsHome";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("统计UApp");
        setBackVisibily();

        mContext = this;
        // SDK在统计Fragment时，需要关闭Activity自带的页面统计，
        // 然后在每个页面中重新集成页面统计的代码(包括调用了 onResume 和 onPause 的Activity)。
        MobclickAgent.openActivityDurationTrack(false);
        // MobclickAgent.setAutoLocation(true);
        // MobclickAgent.setSessionContinueMillis(1000);
//        MobclickAgent.setScenarioType(mContext, EScenarioType.E_UM_NORMAL);
        //设置dplus case
        MobclickAgent.setScenarioType(mContext, MobclickAgent.EScenarioType.E_DUM_NORMAL);

        findViewById(R.id.analytics_g1_b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MobclickAgent.onEvent(mContext, "click");
                MobclickAgent.onEvent(mContext, "click", "button");
            }
        });
        findViewById(R.id.analytics_g1_b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> map_ekv = new HashMap<String, String>();
                map_ekv.put("type", "popular");
                map_ekv.put("artist", "JJLin");

                MobclickAgent.onEvent(mContext, "music", map_ekv);
            }
        });
        findViewById(R.id.analytics_g1_b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> map_value = new HashMap<String, String>();
                map_value.put("type", "popular");
                map_value.put("artist", "JJLin");

                MobclickAgent.onEventValue(mContext, "music", map_value, 12000);
            }
        });

        findViewById(R.id.analytics_g2_b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MobclickAgent.onProfileSignIn("example_id");
            }
        });
        findViewById(R.id.analytics_g2_b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MobclickAgent.onProfileSignOff();
            }
        });

        findViewById(R.id.analytics_g3_b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                "123".substring(10);
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
        MobclickAgent.onResume(mContext);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(mPageName);
        MobclickAgent.onPause(mContext);
    }
}
