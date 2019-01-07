package com.umeng.soexample.analytics;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.umeng.analytics.MobclickAgent;

import java.util.Date;


/**
 * Created by feisha on 2018/3/20.
 */

public class Process1Service extends Service{

    public static final String TAG = "Process1Service";
    private Context mContext;
    private Handler mHandler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        this.mContext = this;
        this.mHandler = new Handler();
        int pid = android.os.Process.myPid();
        Log.i(TAG, "当前进程ID: " + pid);
        // 在主线程中发送自定义事件
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Date date = new Date(System.currentTimeMillis());
                String time = date.getHours() + " : " + date.getMinutes() + " : " + date.getSeconds();

                MobclickAgent.onEvent(mContext, "Process1Service:"+time, "Process1Service");

                //mHandler.postDelayed(this, 3000);
            }
        }, 1000);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
