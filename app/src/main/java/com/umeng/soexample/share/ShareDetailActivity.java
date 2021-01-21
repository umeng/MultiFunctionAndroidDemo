package com.umeng.soexample.share;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
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
import com.umeng.socialize.media.UMQQMini;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.soexample.BaseActivity;
import com.umeng.soexample.R;
import com.umeng.soexample.share.utils.Defaultcontent;
import com.umeng.soexample.share.utils.StyleUtil;
import com.umeng.soexample.views.Item;

import com.umeng.socialize.tracker.TrackerManager;
import com.umeng.socialize.tracker.TrackerResultHandler;

/**
 * Created by wangfei on 2018/1/23.
 */

public class ShareDetailActivity extends BaseActivity{

    public ArrayList<String> styles = new ArrayList<String>();
    private SHARE_MEDIA share_media;
    private LinearLayout container;

    private ProgressDialog dialog;

    // 演示企业微信 本地文件、本地视频文件分享
    private File localfile;
    private UMVideo localVideo;
    private Context appContext;
    private UMImage sinaImageLocal_1, sinaImageLocal_2;
    private Handler H;

    // 工具函数
    private boolean writeFile(String filename, InputStream in) throws IOException
    {
        boolean bRet = true;
        try {
            OutputStream os = new FileOutputStream(filename);
            byte[] buffer = new byte[4112];
            int read;
            while((read = in.read(buffer)) != -1)
            {
                os.write(buffer, 0, read);
            }
            in.close();
            in = null;
            os.flush();
            os.close();
            os = null;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            bRet = false;
        }
        return bRet;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appContext = this.getApplicationContext();
        share_media = (SHARE_MEDIA) getIntent().getSerializableExtra("platform");
        String name = getIntent().getStringExtra("name");
        setTitle(name);
        setBackVisibily();
        initViews();
        H = new Handler(Looper.getMainLooper());
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
                    }else if (style.equals(StyleUtil.WEB000)){
                        shareUrlWithRootTrackCode(appContext);
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
                    }else if (style.equals(StyleUtil.LOCALVIDEO)) {
                        shareLocalVideo();
                    }else if (style.equals(StyleUtil.LOCALFILE)) {
                        shareLocalFile();
                    }else if (style.equals(StyleUtil.MULIMAGE)){
                        shareMulImage();
                    }else if (style.equals(StyleUtil.QQMiniApp)) {
                        shareQQMiniApp();
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

    // ROOT Track Code获取功能见说明：https://developer.umeng.com/docs/128606/detail/200739
    public void shareUrlWithRootTrackCode(final Context context) {
        final String webUrl = Defaultcontent.url;
        HashMap<String, String> customParam = new HashMap<String, String>();
        customParam.put("userId", "123566");
        customParam.put("website", "baidu.com");
        customParam.put("0123456789abcdefg", "key_length_too_long");
        customParam.put("值长度超限", "012345678901234567890123456789012345678901234567890123456789abcde");
        customParam.put("valueIsEmpty", "");
        String umid = TrackerManager.getUMID(context);
        if (TextUtils.isEmpty(umid)) {
            Toast.makeText(context,"UMID获取失败，请稍后重试！", Toast.LENGTH_LONG).show();
            return;
        }
        TrackerManager.requestTrackerCode(context,
                "59892f08310c9307b60023d0", umid, webUrl, null, customParam,
                new TrackerResultHandler() {
                    @Override
                    public void codeGenerateSuccess(final String rootTrackCode) {
                        if (H != null) {
                            H.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(appContext,"获取rootTrackCode 成功了。", Toast.LENGTH_LONG).show();
                                    String newWebUrl = webUrl + "?um_rtc=" + rootTrackCode;
                                    UMWeb rootTrackWeb = new UMWeb(newWebUrl);
                                    rootTrackWeb.setTitle("Web title with root track code.");
                                    rootTrackWeb.setThumb(new UMImage(ShareDetailActivity.this, R.drawable.thumb ));
                                    rootTrackWeb.setDescription("root track code sample.");

                                    new ShareAction(ShareDetailActivity.this)
                                            .withMedia(rootTrackWeb)
                                            .setPlatform(share_media)
                                            .setCallback(shareListener).share();
                                }
                            });
                        }

                    }

                    @Override
                    public void codeGenerateFailed(Throwable t) {
                        if (H != null) {
                            final String errorMsg = t.getMessage();
                            H.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(appContext,"获取rootTrackCode 失败了: " + errorMsg, Toast.LENGTH_LONG).show();
                                    UMWeb web = new UMWeb(Defaultcontent.url);
                                    web.setTitle("This is web title");
                                    web.setThumb(new UMImage(ShareDetailActivity.this, R.drawable.thumb));
                                    web.setDescription("my description");
                                    new ShareAction(ShareDetailActivity.this)
                                            .withMedia(web)
                                            .setPlatform(share_media)
                                            .setCallback(shareListener).share();
                                }
                            });
                        }

                    }
                });
    }

    public void shareMusic(){
        UMusic music = new UMusic(Defaultcontent.musicurl);
        music.setTitle("This is music title");
        music.setThumb(new UMImage(this, R.drawable.thumb));
        music.setDescription("my description");
        music.setmTargetUrl(Defaultcontent.musicurl);
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

    public void shareLocalFile() {
        if (localfile == null) {
            String umengCacheDir = this.getExternalFilesDir(null) + File.separator + "umeng_cache";
            File dir = new File(umengCacheDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String localFilePath = umengCacheDir + File.separator + "localFile.txt";
            localfile = new File(localFilePath);
            if (!localfile.exists()) {
                try {
                    localfile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (SocializeUtils.File2byte(localfile).length <= 0) {
                String content = "U-share分享";
                byte[] contentInBytes = content.getBytes();
                try {
                    FileOutputStream fop = new FileOutputStream(localfile);
                    fop.write(contentInBytes);
                    fop.flush();
                    fop.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        new ShareAction(ShareDetailActivity.this)
                .withFile(localfile)
                .withText(Defaultcontent.text)
                .withSubject(Defaultcontent.title)
                .setPlatform(share_media)
                .setCallback(shareListener).share();

    }

    public void shareLocalVideo() {
        //UMVideo localVideo = new UMVideo();
        if (localVideo == null) {
            String umengCacheDir  = this.getExternalFilesDir(null) + File.separator + "umeng_cache";
            File dir = new File(umengCacheDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String localVidemoFileName = "localvideo.mp4";
            AssetManager am = this.getAssets();
            try {
                InputStream is = am.open(localVidemoFileName);

                boolean result = writeFile(umengCacheDir + File.separator + localVidemoFileName, is);
                if (result) {
                    File video = new File(umengCacheDir + File.separator + localVidemoFileName);
                    localVideo = new UMVideo(video);
                }

            } catch (Throwable e) {

            }
        }
        new ShareAction(ShareDetailActivity.this)
                .withMedia(localVideo)
                .withText(Defaultcontent.text)
                .withSubject(Defaultcontent.title)
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


        if(share_media == SHARE_MEDIA.SINA){
            // 新浪微博多图分享仅支持共享路径 /data/data/应用包名/files/ 下图片文件
            //将图片copy到/data/data/应用包名/files/下
            prepareSinaImages();
            File sinaImage_1 = new File(getExternalFilesDir(null) + "/datu.jpg");
            File sinaImage_2 = new File(getExternalFilesDir(null) + "/logo.png");
            sinaImageLocal_1 = new UMImage(this, sinaImage_1);
            sinaImageLocal_2 = new UMImage(this, sinaImage_2);
            // 通过FileProvider方式跨App分享图片时，

            new ShareAction(ShareDetailActivity.this).withText("多图分享").withMedias(sinaImageLocal_1, sinaImageLocal_2)
                    .setPlatform(share_media)
                    .setCallback(shareListener).share();

        }else {
            UMImage imagelocal1 = new UMImage(this, R.drawable.logo);
            imagelocal1.setThumb(new UMImage(this, R.drawable.thumb));
            UMImage imagelocal2 = new UMImage(this, R.drawable.datu);
            imagelocal2.setThumb(new UMImage(this, R.drawable.datu));

            new ShareAction(ShareDetailActivity.this).withText("多图分享").withMedias(imagelocal1,imagelocal2 )
                    .setPlatform(share_media)
                    .setCallback(shareListener).share();
        }


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
        UMEmoji emoji = new UMEmoji(this,R.drawable.tutu);
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

    public void shareQQMiniApp() {
        UMQQMini qqMini = new UMQQMini(Defaultcontent.url);
        qqMini.setThumb(new UMImage(this, Defaultcontent.imageurl)); // 缩略图支持网络图片和本地图片
        qqMini.setTitle(Defaultcontent.title);
        qqMini.setDescription(Defaultcontent.text);
        qqMini.setMiniAppId("1110429485");
        qqMini.setPath("pages/index/index");
        new ShareAction(ShareDetailActivity.this)
                .withMedia(qqMini)
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


    private void copyFile(final String fileName) {
        final File file = new File(getExternalFilesDir(null).getPath() + "/" + fileName);
        if (!file.exists()) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream inputStream = getAssets().open(fileName);
                        OutputStream outputStream = new FileOutputStream(file);
                        byte[] buffer = new byte[1444];
                        int readSize;
                        while ((readSize = inputStream.read(buffer)) != 0) {
                            outputStream.write(buffer, 0, readSize);
                        }
                        inputStream.close();
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
    private void prepareSinaImages() {
        copyFile("logo.png");
        copyFile("datu.jpg");
    }



}
