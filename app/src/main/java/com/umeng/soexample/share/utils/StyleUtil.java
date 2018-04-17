package com.umeng.soexample.share.utils;

import java.util.ArrayList;

import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by wangfei on 2018/1/23.
 */

public class StyleUtil {

    public static String TEXT = "纯文本";
    public static String IMAGELOCAL = "纯图片本地";
    public static String IMAGEURL = "纯图片http";
    public static String TEXTANDIMAGE = "图文";
    public static String MULIMAGE = "多图分享";
    public static String  MUSIC00= "音乐（无标题，无内容）";
    public static String  MUSIC11= "音乐（有标题，有内容）";
    public static String  MUSIC10= "音乐（有标题，无内容）";
    public static String  MUSIC01= "音乐（无标题，有内容）";
    public static String  VIDEO00= "视频（无标题，无内容）";
    public static String  VIDEO11= "视频（有标题，有内容）";
    public static String  VIDEO10= "视频（有标题，无内容）";
    public static String  VIDEO01= "视频（无标题，有内容）";
    public static String  WEB00= "链接（无标题，无内容）";
    public static String  WEB11= "链接（有标题，有内容）";
    public static String  WEB10= "链接（有标题，无内容）";
    public static String  WEB01= "链接（无标题，有内容）";
    public static String  EMOJI= "微信表情";
    public static String  FILE= "文件";
    public static String  MINAPP= "小程序（测试）";
    public static void initStyles(SHARE_MEDIA share_media, ArrayList<String> styles){
        styles.clear();
        if (share_media == SHARE_MEDIA.QQ){
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.QZONE){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.MULIMAGE);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.SINA){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.TEXTANDIMAGE);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.MULIMAGE);
            styles.add(StyleUtil.WEB11);
            //            styles.add(StyleUtil.MUSIC11);
            //            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.WEIXIN){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
            styles.add(StyleUtil.EMOJI);
            styles.add(StyleUtil.MINAPP);
        }
        else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }
        else if (share_media == SHARE_MEDIA.WEIXIN_FAVORITE){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);

        }  else if (share_media == SHARE_MEDIA.TENCENT){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        } else if (share_media == SHARE_MEDIA.DOUBAN){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.TEXTANDIMAGE);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.RENREN){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.TEXTANDIMAGE);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.ALIPAY){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.FACEBOOK){
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.FACEBOOK_MESSAGER){
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.TWITTER){
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.EMAIL){
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.SMS){
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.YIXIN){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.YIXIN_CIRCLE){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.LAIWANG){

            //            styles.add(StyleUtil.IMAGELOCAL);
            //            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.LAIWANG_DYNAMIC){
            //
            //            styles.add(StyleUtil.IMAGELOCAL);
            //            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.INSTAGRAM){
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
        }else if (share_media == SHARE_MEDIA.PINTEREST){
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
        }else if (share_media == SHARE_MEDIA.TUMBLR){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);

        }else if (share_media == SHARE_MEDIA.LINE){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);

        }else if (share_media == SHARE_MEDIA.WHATSAPP){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.KAKAO){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.GOOGLEPLUS){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.EVERNOTE){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.YNOTE){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.FLICKR){

            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);

        }else if (share_media == SHARE_MEDIA.LINKEDIN){
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.POCKET) {
            styles.add(StyleUtil.WEB00);
        }else if (share_media == SHARE_MEDIA.FOURSQUARE) {
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
        }else if (share_media == SHARE_MEDIA.MORE) {
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.TEXTANDIMAGE);
        }else if (share_media == SHARE_MEDIA.DINGTALK) {
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.VKONTAKTE) {
            styles.add(StyleUtil.TEXT);
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
            styles.add(StyleUtil.WEB11);
            styles.add(StyleUtil.MUSIC11);
            styles.add(StyleUtil.VIDEO11);
        }else if (share_media == SHARE_MEDIA.DROPBOX) {
            styles.add(StyleUtil.IMAGELOCAL);
            styles.add(StyleUtil.IMAGEURL);
        }

    }
}