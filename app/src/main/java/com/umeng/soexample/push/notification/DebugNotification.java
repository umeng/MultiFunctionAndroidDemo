package com.umeng.soexample.push.notification;

import java.security.MessageDigest;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.util.HttpRequest;
import org.json.JSONObject;

public class DebugNotification {

    /**
     * 发送透传消息
     *
     * @param mContext
     */
    public static void transmission(final Context mContext, final Handler handler) {
        try {
            final AndroidUnicast unicast = new AndroidUnicast("59892f08310c9307b60023d0",
                "xkqdlqwgkglgfdydyawb16etxilvmy3g");
            unicast.setDeviceToken(MessageSharedPrefs.getInstance(mContext).getDeviceToken());
            unicast.setTicker("Android unicast ticker");
            unicast.setTitle("Title");
            unicast.setText("Demo透传测试");
            unicast.setPlaySound(true);
            unicast.goAppAfterOpen();
            unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
            unicast.setProductionMode();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        send(unicast, mContext, handler);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {

        }
    }

    public static void send(UmengNotification msg, final Context mContext, Handler handler) throws Exception {
        String timestamp = Integer.toString((int)(System.currentTimeMillis() / 1000));
        msg.setPredefinedKeyValue("timestamp", timestamp);

        String url = "http://msg.umeng.com/api/send";
        String postBody = msg.getPostBody();

        String p_sign = "POST" + url + postBody + msg.getAppMasterSecret();
        String sign = md5(p_sign);
        url = url + "?sign=" + sign;

        String response = HttpRequest.post(url).acceptJson()
            .send(postBody).body("UTF-8");
        JSONObject responseJson = new JSONObject(response);
        String ret = responseJson.getString("ret");

        if (!ret.equalsIgnoreCase("SUCCESS")) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, "透传发送失败", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, "透传发送成功", Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    public static String md5(String string) {
        byte[] hash = null;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);

        for (byte b : hash) {
            int i = (b & 0xFF);
            if (i < 0x10) { hex.append('0'); }
            hex.append(Integer.toHexString(i));
        }

        return hex.toString();
    }
}
