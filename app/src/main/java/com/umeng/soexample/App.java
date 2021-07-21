package com.umeng.soexample;


import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.taobao.accs.ACCSClient;
import com.taobao.accs.AccsClientConfig;
import com.taobao.agoo.TaobaoRegister;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.PlatformConfig;

import com.umeng.analytics.MobclickAgent;
import com.umeng.soexample.push.UmengNotificationService;
import com.umeng.soexample.sp.SharedPreferencesHelper;

/**
 * Created by wangfei on 2018/1/23.
 */

public class App extends Application {

    SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferencesHelper=new SharedPreferencesHelper(this,"umeng");

        //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(true);

        try {
            //解决推送消息显示乱码的问题
            AccsClientConfig.Builder builder = new AccsClientConfig.Builder();
            builder.setAppKey("umeng:" + "59892f08310c9307b60023d0");
            builder.setAppSecret("669c30a9584623e70e8cd01b0381dcb4");
            builder.setTag(AccsClientConfig.DEFAULT_CONFIGTAG);
            ACCSClient.init(getApplicationContext(), builder.build());
            TaobaoRegister.setAccsConfigTag(getApplicationContext(), AccsClientConfig.DEFAULT_CONFIGTAG);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //友盟预初始化
        UMConfigure.preInit(getApplicationContext(),"59892f08310c9307b60023d0","Umeng");

        /**
         * 打开app首次隐私协议授权，以及sdk初始化，判断逻辑请查看SplashTestActivity
         */
        //判断是否同意隐私协议，uminit为1时为已经同意，直接初始化umsdk
        if(sharedPreferencesHelper.getSharedPreference("uminit","").equals("1")){
            //友盟正式初始化
            UmInitConfig umInitConfig=new UmInitConfig();
            umInitConfig.UMinit(getApplicationContext());
        }


    }
}
