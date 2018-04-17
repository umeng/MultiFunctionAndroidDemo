package com.umeng.soexample.share;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
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
import com.umeng.soexample.views.Item;

/**
 * Created by wangfei on 2018/1/23.
 */

public class ShareDetailActivity extends BaseActivity{

    public ArrayList<String> styles = new ArrayList<String>();
    private SHARE_MEDIA share_media;
    private LinearLayout container;

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
        LinearLayout container = (LinearLayout)findViewById(R.id.platform_container);
        StyleUtil.initStyles(share_media,styles);
        dialog = new ProgressDialog(this);
        for (final String style:styles){
            Item item = new Item(this);
            item.setName(style);
            item.setNoIcon();
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getResources().getDimensionPixelOffset(R.dimen.item_height));
            item.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (style.startsWith(StyleUtil.TEXT)){
                        shareText();
                    }else if (style.equals(StyleUtil.IMAGELOCAL)){
                        shareImageLocal();
                    }else if (style.equals(StyleUtil.IMAGEURL)){
                        shareImageNet();
                    }else if (style.startsWith(StyleUtil.WEB00.substring(0,2))){
                        shareUrl();
                    }else if (style.startsWith(StyleUtil.MUSIC00.substring(0,2))){
                       shareMusic();
                    }else if (style.startsWith(StyleUtil.VIDEO00.substring(0,2))){
                        shareVideo();
                    }else if (style.equals(StyleUtil.TEXTANDIMAGE)){
                        shareTextAndImage();
                    }else if (style.equals(StyleUtil.MINAPP)){
                        shareMINApp();
                    }else if (style.equals(StyleUtil.EMOJI)){
                        shareEmoji();
                    }else if (style.equals(StyleUtil.FILE)){
                        shareFile();
                    }else if (style.equals(StyleUtil.MULIMAGE)){
                        shareMulImage();
                    }
                }
            });
            item.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (style.startsWith(StyleUtil.TEXT)
                        ||style.equals(StyleUtil.IMAGELOCAL)
                        ||style.equals(StyleUtil.IMAGEURL)
                        ||style.startsWith(StyleUtil.WEB00.substring(0,2))){
                        Intent intent = new Intent(ShareDetailActivity.this,SettingActivity.class);
                        intent.putExtra("style",style);
                        intent.putExtra("platform",share_media);
                        ShareDetailActivity.this.startActivity(intent);

                    }
                    return false;
                }
            });
            container.addView(item,lp);
        }


    }
    public void shareText(){
        new ShareAction(ShareDetailActivity.this).withText(Defaultcontent.text)
            .setPlatform(share_media)
            .setCallback(shareListener).share();
    }
    public void shareImageLocal(){
        UMImage imagelocal = new UMImage(this, R.drawable.logo);
        imagelocal.setThumb(new UMImage(this, R.drawable.thumb));
        new ShareAction(ShareDetailActivity.this).withMedia(imagelocal )
            .setPlatform(share_media)
            .setCallback(shareListener).share();
    }
    public void shareImageNet(){
        UMImage imageurl = new UMImage(this,Defaultcontent.imageurl);
        imageurl.setThumb(new UMImage(this, R.drawable.thumb));
        new ShareAction(ShareDetailActivity.this).withMedia(imageurl )
            .setPlatform(share_media)
            .setCallback(shareListener).share();
    }
    public void shareUrl(){
        UMWeb web = new UMWeb(Defaultcontent.url);
        web.setTitle("This is web title");
        web.setThumb(new UMImage(this, R.drawable.thumb));
        web.setDescription("my description");
        new ShareAction(ShareDetailActivity.this).withMedia(web )
            .setPlatform(share_media)
            .setCallback(shareListener).share();
    }
    public void shareMusic(){
        UMusic music = new UMusic(Defaultcontent.musicurl);
        music.setTitle("This is music title");
        music.setThumb(new UMImage(this, R.drawable.thumb));
        music.setDescription("my description");
        music.setmTargetUrl(Defaultcontent.url);
        new ShareAction(ShareDetailActivity.this).withMedia(music )
            .setPlatform(share_media)
            .setCallback(shareListener).share();
    }
    public void shareVideo(){
        UMVideo video = new UMVideo(Defaultcontent.videourl);
        video.setThumb(new UMImage(this,R.drawable.thumb));
        video.setTitle("This is video title");
        video.setDescription("my description");
        new ShareAction(ShareDetailActivity.this).withMedia(video )
            .setPlatform(share_media)
            .setCallback(shareListener).share();
    }
    public void shareTextAndImage(){
        UMImage imagelocal = new UMImage(this, R.drawable.logo);
        imagelocal.setThumb(new UMImage(this, R.drawable.thumb));
        new ShareAction(ShareDetailActivity.this).withText(Defaultcontent.text)
            .withMedia(imagelocal)
            .setPlatform(share_media)
            .setCallback(shareListener).share();
    }
    public void shareMulImage(){
        UMImage imagelocal1 = new UMImage(this, R.drawable.logo);
        imagelocal1.setThumb(new UMImage(this, R.drawable.thumb));
        UMImage imagelocal2 = new UMImage(this, R.drawable.thumb);
        imagelocal2.setThumb(new UMImage(this, R.drawable.thumb));
        new ShareAction(ShareDetailActivity.this).withText("多图分享").withMedias(imagelocal1,imagelocal2 )
            .setPlatform(share_media)
            .setCallback(shareListener).share();
    }
    public void shareFile(){
        File file = new File(this.getFilesDir()+"test.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (SocializeUtils.File2byte(file).length<=0){
            String content = "U-share分享";
            byte[] contentInBytes = content.getBytes();
            try {
                FileOutputStream fop = new FileOutputStream(file);
                fop.write(contentInBytes);
                fop.flush();
                fop.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        new ShareAction(ShareDetailActivity.this)
            .withFile(file)
            .withText(Defaultcontent.text)
            .withSubject(Defaultcontent.title)
            .setPlatform(share_media)
            .setCallback(shareListener).share();
    }
    public void shareEmoji(){
        UMEmoji emoji = new UMEmoji(this,"http://img5.imgtn.bdimg.com/it/u=2749190246,3857616763&fm=21&gp=0.jpg");
        emoji.setThumb(new UMImage(this, R.drawable.thumb));
        new ShareAction(ShareDetailActivity.this)
            .withMedia(emoji)
            .setPlatform(share_media)
            .setCallback(shareListener).share();
    }
    public void shareMINApp(){
        UMMin umMin = new UMMin(Defaultcontent.url);
        umMin.setThumb(new UMImage(this,R.drawable.thumb));
        umMin.setTitle(Defaultcontent.title);
        umMin.setDescription(Defaultcontent.text);
        umMin.setPath("pages/page10007/page10007");
        umMin.setUserName("gh_3ac2059ac66f");
        new ShareAction(ShareDetailActivity.this)
            .withMedia(umMin)
            .setPlatform(share_media)
            .setCallback(shareListener).share();
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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }
}
