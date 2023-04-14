package com.umeng.soexample.share;

import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.handler.UMDYUtils;
import com.umeng.socialize.handler.UMHonorUtils;
import com.umeng.soexample.BaseActivity;
import com.umeng.soexample.R;

/**
 * Created by wangfei on 2018/1/23.
 */

public class InfoDetailActivity extends BaseActivity {
    private SHARE_MEDIA share_media;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        share_media = (SHARE_MEDIA) getIntent().getSerializableExtra("platform");
        setTitle("获取用户资料");
        setBackVisibily();
        result = findViewById(R.id.result);
        result.setMovementMethod(new ScrollingMovementMethod());
        findViewById(R.id.ok).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                UMShareAPI.get(InfoDetailActivity.this).getPlatformInfo(InfoDetailActivity.this, share_media, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                        Log.e("InfoDetailActivity",data+"");
                        StringBuilder temp = new StringBuilder();
                        for (String key : data.keySet()) {
                            String v = data.get(key);
                            if (platform == SHARE_MEDIA.BYTEDANCE) {
                                if ("encrypt_mobile".equals(key)) {
                                    try {
                                        v = UMDYUtils.decryptMobileNumber(v);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else if (platform == SHARE_MEDIA.HONOR) {
                                if ("mobileNumber".equals(key)) {
                                    try {
                                        v = UMHonorUtils.decryptMobileNumber(v);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            temp.append(key).append(" : ").append(v).append("\n");
                        }
                        result.setText(temp.toString());
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        result.setText("错误" + throwable.getMessage());
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        result.setText("用户已取消");
                    }
                });
            }
        });
        findViewById(R.id.clear).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("");
            }
        });
    }
    @Override
    public int getLayout() {
        return R.layout.activity_infodetail;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }
}
