package com.umeng.soexample.share;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.umeng.soexample.R;
import com.umeng.soexample.share.utils.StyleUtil;

/**
 * Created by wangfei on 2018/1/24.
 */

public class SettingActivity extends Activity{
    private String style;
    private RelativeLayout textContainer,netImgContainer,localImgContainer,netContainer,btnGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        style = getIntent().getStringExtra("style");
        textContainer = (RelativeLayout)findViewById(R.id.text_container);
        netImgContainer = (RelativeLayout)findViewById(R.id.netimg_container);
        localImgContainer = (RelativeLayout)findViewById(R.id.localimg_container);
        netContainer = (RelativeLayout)findViewById(R.id.net_container);
        btnGroup = (RelativeLayout)findViewById(R.id.btnContainer);
        initViews();
    }
    private void initViews(){
        LayoutParams lp = (LayoutParams)btnGroup.getLayoutParams();
        if (style.startsWith(StyleUtil.TEXT)){
            textContainer.setVisibility(View.VISIBLE);
            lp.addRule(RelativeLayout.BELOW, textContainer.getId());
            btnGroup.setLayoutParams(lp);
        }else if (style.equals(StyleUtil.IMAGELOCAL)){
            localImgContainer.setVisibility(View.VISIBLE);
            lp.addRule(RelativeLayout.BELOW, localImgContainer.getId());
            btnGroup.setLayoutParams(lp);
        }else if (style.equals(StyleUtil.IMAGEURL)){
            netImgContainer.setVisibility(View.VISIBLE);
            lp.addRule(RelativeLayout.BELOW, netImgContainer.getId());
            btnGroup.setLayoutParams(lp);
        }else if (style.startsWith(StyleUtil.WEB00.substring(0,2))){
            netContainer.setVisibility(View.VISIBLE);
            lp.addRule(RelativeLayout.BELOW, netContainer.getId());
            btnGroup.setLayoutParams(lp);
        }
    }
}
