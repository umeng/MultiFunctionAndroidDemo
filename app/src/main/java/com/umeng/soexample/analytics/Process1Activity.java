package com.umeng.soexample.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.umeng.analytics.MobclickAgent;
import com.umeng.soexample.R;

import java.util.Date;

/**
 * Created by umeng on 2018/3/27.
 */

public class Process1Activity extends Activity{

    public static final String TAG = "Process1Activity";
    private Context mContext;

    private Button mKillProcessBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_1);

        mContext = this;
        int pid = android.os.Process.myPid();
        Log.i(TAG, "当前进程ID: " + pid);

        Button eventBtn = (Button) findViewById(R.id.process_event_btn);

        eventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 主线程中自定义事件埋点
                MobclickAgent.onEvent(mContext, "click", "Process1Activity");
            }
        });

        mKillProcessBtn = (Button) findViewById(R.id.process_stop_process_btn);
        mKillProcessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Runtime.getRuntime().exit(0);
                    }
                },500);
                finish();
            }
        });
    }


}
