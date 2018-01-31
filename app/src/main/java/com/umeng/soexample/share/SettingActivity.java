package com.umeng.soexample.share;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.soexample.R;
import com.umeng.soexample.share.utils.StyleUtil;

/**
 * Created by wangfei on 2018/1/24.
 */

public class SettingActivity extends Activity{
    private String style;
    private RelativeLayout textContainer,netImgContainer,localImgContainer,netContainer,btnGroup;
    private Button sendBtn,cancelBtn;
    private SHARE_MEDIA share_media;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        style = getIntent().getStringExtra("style");
        share_media = (SHARE_MEDIA) getIntent().getSerializableExtra("platform");
        textContainer = (RelativeLayout)findViewById(R.id.text_container);
        netImgContainer = (RelativeLayout)findViewById(R.id.netimg_container);
        localImgContainer = (RelativeLayout)findViewById(R.id.localimg_container);
        netContainer = (RelativeLayout)findViewById(R.id.net_container);
        btnGroup = (RelativeLayout)findViewById(R.id.btnContainer);
        sendBtn=findViewById(R.id.send);
        cancelBtn=findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initViews();
    }
    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(SettingActivity.this,"成功了",Toast.LENGTH_LONG).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {

            Toast.makeText(SettingActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

            Toast.makeText(SettingActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };
    private void initViews(){
        LayoutParams lp = (LayoutParams)btnGroup.getLayoutParams();
        if (style.startsWith(StyleUtil.TEXT)){
            textContainer.setVisibility(View.VISIBLE);
            final EditText editText = findViewById(R.id.text_input);
            lp.addRule(RelativeLayout.BELOW, textContainer.getId());
            btnGroup.setLayoutParams(lp);
            sendBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                   String text = editText.getText().toString();
                    if (!TextUtils.isEmpty(text)){
                        new ShareAction(SettingActivity.this).withText(text)
                            .setPlatform(share_media)
                            .setCallback(shareListener).share();
                    }else {
                        Toast.makeText(SettingActivity.this,"参数不完整",Toast.LENGTH_LONG).show();
                    }

                }
            });

        }else if (style.equals(StyleUtil.IMAGELOCAL)){
            localImgContainer.setVisibility(View.VISIBLE);
            lp.addRule(RelativeLayout.BELOW, localImgContainer.getId());
            btnGroup.setLayoutParams(lp);
            final EditText imgText = findViewById(R.id.localimg_input);
            final EditText thumbText = findViewById(R.id.localimg_input_thumb);

            sendBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = imgText.getText().toString();
                    String thumb = thumbText.getText().toString();
                    if (!TextUtils.isEmpty(text)){
                        File file = new File(text);
                        if (!file.exists()){
                            Toast.makeText(SettingActivity.this,"文件不存在",Toast.LENGTH_LONG).show();
                            return;
                        }
                        UMImage img = new UMImage(SettingActivity.this,file);
                        img.setThumb(getImageThumb(thumb));

                        new ShareAction(SettingActivity.this).withMedia(img)
                            .setPlatform(share_media)
                            .setCallback(shareListener).share();
                    }else {
                        Toast.makeText(SettingActivity.this,"参数不完整",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else if (style.equals(StyleUtil.IMAGEURL)){
            netImgContainer.setVisibility(View.VISIBLE);
            lp.addRule(RelativeLayout.BELOW, netImgContainer.getId());
            btnGroup.setLayoutParams(lp);
            final EditText imgText = findViewById(R.id.netimg_input);
            final EditText thumbText = findViewById(R.id.netimg_input_thumb);
            sendBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = imgText.getText().toString();
                    String thumb = thumbText.getText().toString();
                    if (!TextUtils.isEmpty(text)){

                        UMImage img = new UMImage(SettingActivity.this,text);
                        img.setThumb(getImageThumb(thumb));

                        new ShareAction(SettingActivity.this).withMedia(img)
                            .setPlatform(share_media)
                            .setCallback(shareListener).share();
                    }else {
                        Toast.makeText(SettingActivity.this,"参数不完整",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else if (style.startsWith(StyleUtil.WEB00.substring(0,2))){
            netContainer.setVisibility(View.VISIBLE);
            lp.addRule(RelativeLayout.BELOW, netContainer.getId());
            btnGroup.setLayoutParams(lp);
            final EditText titleText = findViewById(R.id.net_title);
            final EditText descText = findViewById(R.id.net_desc);
            final EditText urlText = findViewById(R.id.net_url);
            final EditText thumbText = findViewById(R.id.net_thumb);
            sendBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title = titleText.getText().toString();
                    String desc = descText.getText().toString();
                    String url = urlText.getText().toString();
                    String thumb = thumbText.getText().toString();
                    if (!TextUtils.isEmpty(title)
                    && !TextUtils.isEmpty(desc)
                        &&!TextUtils.isEmpty(url) ){
                        UMWeb web =  new UMWeb(url);
                        web.setTitle(title);
                        web.setDescription(desc);
                       web.setThumb(getImageThumb(thumb));

                        new ShareAction(SettingActivity.this).withMedia(web)
                            .setPlatform(share_media)
                            .setCallback(shareListener).share();
                    }else {
                        Toast.makeText(SettingActivity.this,"参数不完整",Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }
    private UMImage getImageThumb(String s){
        if (TextUtils.isEmpty(s)){
            return new UMImage(this,R.mipmap.ic_launcher);
        }else if (s.startsWith("/")){
            File file = new File(s);
            if (file.exists()){
                return new UMImage(this,file);
            }else {
                return new UMImage(this,R.mipmap.ic_launcher);
            }
        }else if (s.startsWith("http")){
            return new UMImage(this,s);
        }else {
            return new UMImage(this,R.mipmap.ic_launcher);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }
}
