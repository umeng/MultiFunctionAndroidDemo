package com.umeng.soexample.push;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.umeng.message.inapp.InAppMessageManager;
import com.umeng.message.inapp.UmengSplashMessageActivity;
import com.umeng.soexample.HomeActivity;
import com.umeng.soexample.R;
import com.umeng.soexample.UmInitConfig;
import com.umeng.soexample.share.SharePlatformActivity;
import com.umeng.soexample.sp.SharedPreferencesHelper;

public class SplashTestActivity extends UmengSplashMessageActivity {
    View inflate;
    Dialog dialog;
    SharedPreferencesHelper sharedPreferencesHelper;
    @Override
    public boolean onCustomPretreatment() {

        sharedPreferencesHelper=new SharedPreferencesHelper(this,"umeng");

        /*** sp中uminit为1已经同意隐私协议*/
        if(sharedPreferencesHelper.getSharedPreference("uminit","").equals("1")){

            /***
             *  此时初始化在appcation中进行，这里不做任何友盟初始化操作，直接跳转activity
             *
             */
            //推送全屏消息设置（非初始化操作，这是推送全屏消息设置）
            InAppMessageManager mInAppMessageManager = InAppMessageManager.getInstance(SplashTestActivity.this);
            mInAppMessageManager.setInAppMsgDebugMode(true);
            //跳转homeactivity
            mInAppMessageManager.setMainActivityPath("com.umeng.soexample.HomeActivity");

            return super.onCustomPretreatment();
        }else {
            /*** 隐私协议授权弹窗*/
            dialog();

            return true;
        }


    }



    @SuppressLint("ResourceType")
    public void dialog(){
        dialog = new Dialog(this, R.style.dialog);
        inflate = LayoutInflater.from(SplashTestActivity.this).inflate(R.layout.diaologlayout, null);
        TextView succsebtn=(TextView) inflate.findViewById(R.id.succsebtn);
        TextView canclebtn=(TextView) inflate.findViewById(R.id.caclebtn);

        succsebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*** uminit为1时代表已经同意隐私协议，sp记录当前状态*/
                sharedPreferencesHelper.put("uminit","1");

                /*** 友盟sdk正式初始化*/
                UmInitConfig umInitConfig=new UmInitConfig();
                umInitConfig.UMinit(getApplicationContext());

                //关闭弹窗
                dialog.dismiss();

                //跳转到HomeActivity
                Intent intent =new Intent(SplashTestActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();

            }
        });

        canclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //不同意隐私协议，退出app
                android.os.Process.killProcess(android.os.Process.myPid());

            }
        });

        dialog.setContentView(inflate);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);

        dialog.setCancelable(false);
        dialog.show();
    }
}