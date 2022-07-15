package com.umeng.soexample.push;

import android.os.Bundle;
import android.widget.TextView;
import com.umeng.message.UmengNotifyClickActivity;
import com.umeng.message.entity.UMessage;
import com.umeng.soexample.R;

public class MipushTestActivity extends UmengNotifyClickActivity {

    private TextView mipushTextView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mipush);
        mipushTextView = (TextView) findViewById(R.id.mipushTextView);
    }

    @Override
    protected void onMessage(final UMessage uMessage) {
        super.onMessage(uMessage);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mipushTextView.setText(uMessage.getRaw().toString());
            }
        });
    }

}
