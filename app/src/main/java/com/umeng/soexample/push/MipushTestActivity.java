package com.umeng.soexample.push;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import com.umeng.message.UmengNotifyClickActivity;
import com.umeng.soexample.R;
import org.android.agoo.common.AgooConstants;

public class MipushTestActivity extends UmengNotifyClickActivity {
    private static String TAG = MipushTestActivity.class.getName();
    private TextView mipushTextView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mipush);
        mipushTextView = (TextView) findViewById(R.id.mipushTextView);
    }

    @Override
    public void onMessage(Intent intent) {
        super.onMessage(intent);
        final String body = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
        if (!TextUtils.isEmpty(body)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mipushTextView.setText(body);
                }
            });
        }
    }
}
