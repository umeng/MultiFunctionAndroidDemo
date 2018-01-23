package com.umeng.soexample.share;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.soexample.BaseActivity;
import com.umeng.soexample.R;
import com.umeng.soexample.share.views.ShareAdapter;

/**
 * Created by wangfei on 2018/1/23.
 */

public class InfoActivity extends BaseActivity {
    private ListView listView;
    private ShareAdapter shareAdapter;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ,SHARE_MEDIA.SINA,SHARE_MEDIA.WEIXIN,
        SHARE_MEDIA.FACEBOOK,SHARE_MEDIA.TWITTER,SHARE_MEDIA.LINKEDIN,SHARE_MEDIA.KAKAO,SHARE_MEDIA.VKONTAKTE,SHARE_MEDIA.DROPBOX};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("获取用户资料");
        setBackVisibily();
        initViews();
    }
    private void initViews(){
        listView = (ListView) findViewById(R.id.list);
        initPlatforms();
        shareAdapter  = new ShareAdapter(this,platforms);
        listView.setAdapter(shareAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(InfoActivity.this,InfoDetailActivity.class);
                intent.putExtra("platform",platforms.get(position).mPlatform);
                InfoActivity.this.startActivity(intent);
            }
        });
    }
    @Override
    public int getLayout() {
        return R.layout.activity_ushareplatform;
    }
    private void initPlatforms() {
        platforms.clear();
        for (SHARE_MEDIA e : list) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }
    }


}
