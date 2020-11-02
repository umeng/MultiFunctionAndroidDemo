package com.umeng.soexample.share;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.ResContainer;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.soexample.BaseActivity;
import com.umeng.soexample.R;
import com.umeng.soexample.views.Item;

/**
 * Created by wangfei on 2018/1/23.
 */

public class InfoActivity extends BaseActivity {
    private LinearLayout container;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ,SHARE_MEDIA.SINA,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WXWORK,
        SHARE_MEDIA.FACEBOOK,SHARE_MEDIA.LINKEDIN,SHARE_MEDIA.KAKAO,SHARE_MEDIA.VKONTAKTE,SHARE_MEDIA.DROPBOX};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("获取用户资料");
        setBackVisibily();
        initViews();
    }
    private void initViews(){
        LinearLayout container = (LinearLayout)findViewById(R.id.platform_container);
        initPlatforms();
        for (final SnsPlatform platform:platforms){
            Item item = new Item(this);
            item.setIcon(ResContainer.getResourceId(this,"drawable",platform.mIcon));
            item.setName(platform.mShowWord);
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getResources().getDimensionPixelOffset(R.dimen.item_height));
            item.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(InfoActivity.this,InfoDetailActivity.class);
                    intent.putExtra("platform",platform.mPlatform);
                    InfoActivity.this.startActivity(intent);
                }
            });
            container.addView(item,lp);
        }


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
