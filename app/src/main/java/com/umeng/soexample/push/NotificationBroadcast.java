package com.umeng.soexample.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.umeng.message.UTrack;
import com.umeng.message.entity.UMessage;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationBroadcast extends BroadcastReceiver {
    public static final String EXTRA_KEY_ACTION = "ACTION";
    public static final String EXTRA_KEY_MSG = "MSG";
    public static final int ACTION_CLICK = 10;
    public static final int ACTION_DISMISS = 11;
    public static final int EXTRA_ACTION_NOT_EXIST = -1;
    private static final String TAG = NotificationBroadcast.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra(EXTRA_KEY_MSG);
        int action = intent.getIntExtra(EXTRA_KEY_ACTION,
                EXTRA_ACTION_NOT_EXIST);
        try {
            UMessage msg = (UMessage) new UMessage(new JSONObject(message));

            switch (action) {
                case ACTION_DISMISS:
                    Log.i(TAG, "dismiss notification");
                    UTrack.getInstance(context).setClearPrevMessage(true);
                    UTrack.getInstance(context).trackMsgDismissed(msg);
                    break;
                case ACTION_CLICK:
                    Log.i(TAG, "click notification");
                    UTrack.getInstance(context).setClearPrevMessage(true);
                    MyNotificationService.oldMessage = null;
                    UTrack.getInstance(context).trackMsgClick(msg);
                    break;
            }
            //
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
