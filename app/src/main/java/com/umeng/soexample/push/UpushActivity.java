package com.umeng.soexample.push;

import android.os.Bundle;
import android.view.View;
import com.umeng.soexample.BaseActivity;
import com.umeng.soexample.R;

public class UpushActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("推送");
        setBackVisibily();

        initClickListener();
    }

    private void initClickListener() {
        findViewById(R.id.btn_add_tag).setOnClickListener(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_upush;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_tag:
                new PushDialogFragment().show(getFragmentManager(), "haha");
            case R.id.btn_delete_tag:
            case R.id.btn_show_tags:
            case R.id.btn_add_weighted_tag:
            case R.id.btn_delete_weighted_tag:
            case R.id.btn_show_weighted_tags:
            case R.id.btn_add_alias:
            case R.id.btn_delete_alias:

        }
    }
}
