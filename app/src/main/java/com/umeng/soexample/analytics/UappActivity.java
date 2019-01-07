package com.umeng.soexample.analytics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgent.EScenarioType;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.soexample.BaseActivity;
import com.umeng.soexample.R;
import com.umeng.soexample.share.SharePlatformActivity;
import com.umeng.soexample.share.UshareActivity;

import org.json.JSONException;
import org.json.JSONObject;

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
        int pid = android.os.Process.myPid();
        Log.i(mPageName, "当前进程ID: " + pid);


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

        // 预置事件属性相关接口示例
        findViewById(R.id.analytics_g5_b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject pre = new JSONObject();
                try {
                    pre.put("PreProperties-key1", "PreProperties-value1");
                    pre.put("PreProperties-key2", "PreProperties-value2");
                    pre.put("PreProperties-key3", "PreProperties-value3");
                    pre.put("PreProperties-key4", "PreProperties-value4");
                    pre.put("PreProperties-key5", "PreProperties-value5");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MobclickAgent.registerPreProperties(mContext, pre);
                Toast.makeText(mContext, "预置属性已注册", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.analytics_g5_b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MobclickAgent.unregisterPreProperty(mContext, "PreProperties-key1");
                MobclickAgent.unregisterPreProperty(mContext, "PreProperties-key3");
                MobclickAgent.unregisterPreProperty(mContext, "PreProperties-key5");
                Toast.makeText(mContext, "预置属性已部分注销", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.analytics_g5_b3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject preProperties = MobclickAgent.getPreProperties(mContext);
                if (preProperties != null) {
                    if (preProperties.length() > 0) {
                        String properties = preProperties.toString();
                        Toast.makeText(mContext, "当前预置属性: " + properties, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, "当前预置属性为空", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        findViewById(R.id.analytics_g5_b4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MobclickAgent.clearPreProperties(mContext);
                Toast.makeText(mContext, "预置属性已清空", Toast.LENGTH_SHORT).show();
            }
        });

        // 子进程自定义事件埋点示例
        findViewById(R.id.analytics_g6_b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(mContext, Process1Service.class));
                Toast.makeText(mContext, "后台进程Service已启动", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.analytics_g6_b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, Process1Activity.class));
                Toast.makeText(mContext, "后台进程Activity已启动", Toast.LENGTH_SHORT).show();
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
