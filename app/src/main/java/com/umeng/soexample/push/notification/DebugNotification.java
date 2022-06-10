package com.umeng.soexample.push.notification;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.message.MessageSharedPrefs;
import org.json.JSONObject;

public class DebugNotification {

    /**
     * 发送透传消息
     *
     * @param mContext
     */
    public static void transmission(final Context mContext) {
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
                        send(unicast);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {

        }
    }

    public static void send(UmengNotification msg) throws Exception {
        String timestamp = Integer.toString((int) (System.currentTimeMillis() / 1000));
        msg.setPredefinedKeyValue("timestamp", timestamp);

        String url = "http://msg.umeng.com/api/send";
        String postBody = msg.getPostBody();

        String p_sign = "POST" + url + postBody + msg.getAppMasterSecret();
        String sign = md5(p_sign);
        url = url + "?sign=" + sign;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(15000);
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(postBody.getBytes());
        outputStream.close();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream inputStream = connection.getInputStream();
        if (inputStream != null) {
            byte[] buffer = new byte[8 * 1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            inputStream.close();
        }

        connection.disconnect();

        JSONObject responseJson = new JSONObject(out.toString());
        String ret = responseJson.getString("ret");
        Runnable runnable;
        if (!ret.equalsIgnoreCase("SUCCESS")) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(UMGlobalContext.getAppContext(), "发送失败", Toast.LENGTH_LONG).show();
                }
            };
        } else {
            runnable = new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(UMGlobalContext.getAppContext(), "发送成功", Toast.LENGTH_LONG).show();
                }
            };
        }
        new Handler(Looper.getMainLooper()).post(runnable);
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
