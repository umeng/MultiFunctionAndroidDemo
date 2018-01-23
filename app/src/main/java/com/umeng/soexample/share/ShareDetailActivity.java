package com.umeng.soexample.share;

import java.io.File;
import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMEmoji;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMMin;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.soexample.BaseActivity;
import com.umeng.soexample.R;
import com.umeng.soexample.share.utils.Defaultcontent;
import com.umeng.soexample.share.utils.StyleUtil;
import com.umeng.soexample.share.views.ShareTypeAdapter;

/**
 * Created by wangfei on 2018/1/23.
 */

public class ShareDetailActivity extends BaseActivity{
    private ListView listView;
    private ShareTypeAdapter shareAdapter;
    public ArrayList<String> styles = new ArrayList<String>();
    private SHARE_MEDIA share_media;
    private LinearLayout container;
    private UMImage imageurl,imagelocal;
    private UMVideo video;
    private UMusic music;
    private UMEmoji emoji;
    private UMWeb web;
    private File file;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        share_media = (SHARE_MEDIA) getIntent().getSerializableExtra("platform");
        String name = getIntent().getStringExtra("name");
        setTitle(name);
        setBackVisibily();
        initViews();

    }
    private void initViews(){
        listView = (ListView) findViewById(R.id.list);
        StyleUtil.initStyles(share_media,styles);
        dialog = new ProgressDialog(this);
        shareAdapter  = new ShareTypeAdapter(this,styles);
        listView.setAdapter(shareAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (styles.get(position).equals(StyleUtil.IMAGELOCAL)){
                    new ShareAction(ShareDetailActivity.this).withMedia(imagelocal )
                        .setPlatform(share_media)
                        .setCallback(shareListener).share();
                }else if (styles.get(position).equals(StyleUtil.IMAGEURL)){
                    new ShareAction(ShareDetailActivity.this).withMedia(imageurl )
                        .setPlatform(share_media)
                        .setCallback(shareListener).share();
                }else if (styles.get(position).equals(StyleUtil.TEXT)){
                    new ShareAction(ShareDetailActivity.this).withText(Defaultcontent.text)
                        .setPlatform(share_media)
                        .setCallback(shareListener).share();
                }else if (styles.get(position).equals(StyleUtil.TEXTANDIMAGE)){
                    new ShareAction(ShareDetailActivity.this).withText(Defaultcontent.text)
                        .withMedia(imagelocal)
                        .setPlatform(share_media)
                        .setCallback(shareListener).share();
                }else if (styles.get(position).equals(StyleUtil.WEB11)
                    ||styles.get(position).equals(StyleUtil.WEB00)
                    ||styles.get(position).equals(StyleUtil.WEB10)
                    ||styles.get(position).equals(StyleUtil.WEB01)){
                    new ShareAction(ShareDetailActivity.this)
                        .withMedia(web)
                        .setPlatform(share_media)
                        .setCallback(shareListener).share();
                }else if (styles.get(position).equals(StyleUtil.MUSIC11)
                    ||styles.get(position).equals(StyleUtil.MUSIC00)
                    ||styles.get(position).equals(StyleUtil.MUSIC10)
                    ||styles.get(position).equals(StyleUtil.MUSIC01)){
                    new ShareAction(ShareDetailActivity.this).withMedia(music)
                        .setPlatform(share_media)
                        .setCallback(shareListener).share();
                }else if (styles.get(position).equals(StyleUtil.VIDEO11)
                    ||styles.get(position).equals(StyleUtil.VIDEO00)
                    ||styles.get(position).equals(StyleUtil.VIDEO01)
                    ||styles.get(position).equals(StyleUtil.VIDEO10)){
                    new ShareAction(ShareDetailActivity.this).withMedia(video)
                        .setPlatform(share_media)
                        .setCallback(shareListener).share();
                }else if (styles.get(position).equals(StyleUtil.EMOJI)){
                    new ShareAction(ShareDetailActivity.this)
                        .withMedia(emoji)
                        .setPlatform(share_media)
                        .setCallback(shareListener).share();
                }else if (styles.get(position).equals(StyleUtil.FILE)){
                    new ShareAction(ShareDetailActivity.this)
                        .withFile(file)
                        .withText(Defaultcontent.text)
                        .withSubject(Defaultcontent.title)
                        .setPlatform(share_media)
                        .setCallback(shareListener).share();
                }else if (styles.get(position).equals(StyleUtil.MINAPP)){
                    UMMin umMin = new UMMin(Defaultcontent.url);
                    umMin.setThumb(imagelocal);
                    umMin.setTitle(Defaultcontent.title);
                    umMin.setDescription(Defaultcontent.text);
                    umMin.setPath("pages/page10007/page10007");
                    umMin.setUserName("gh_3ac2059ac66f");
                    new ShareAction(ShareDetailActivity.this)
                        .withMedia(umMin)
                        .setPlatform(share_media)
                        .setCallback(shareListener).share();
                }
            }
        });

    }
    @Override
    public int getLayout() {
        return R.layout.activity_usharedetail;
    }
    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(ShareDetailActivity.this,"成功了",Toast.LENGTH_LONG).show();
            SocializeUtils.safeCloseDialog(dialog);
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(ShareDetailActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(ShareDetailActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        listView.clearFocus();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }
}
