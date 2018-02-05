package com.umeng.soexample.push;

import java.util.Hashtable;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.common.UmLog;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.common.inter.ITagManager.Result;
import com.umeng.message.inapp.IUmengInAppMsgCloseCallback;
import com.umeng.message.inapp.InAppMessageManager;
import com.umeng.message.tag.TagManager;
import com.umeng.message.tag.TagManager.TCallBack;
import com.umeng.message.tag.TagManager.WeightedTagListCallBack;
import com.umeng.soexample.BaseActivity;
import com.umeng.soexample.R;
import com.umeng.soexample.push.notification.DebugNotification;

public class UpushActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = UpushActivity.class.getName();

    private EditText inputTag;
    private EditText inputWeightedTag;
    private EditText inputWeightedTagValue;
    private EditText inputAlias;
    private EditText inputAliasType;

    private TextView tagRemain;

    private PushAgent mPushAgent;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("推送");
        setBackVisibily();
        initClickListener();
        handler = new Handler();

        mPushAgent = PushAgent.getInstance(this);
        mPushAgent.onAppStart();

        if (savedInstanceState == null) {
            showCardMessage();
        }
    }

    private void initClickListener() {
        inputTag = findViewById(R.id.etx_input_tag);
        inputWeightedTag = findViewById(R.id.etx_input_weighted_tag);
        inputWeightedTagValue = findViewById(R.id.etx_input_weighted_tag_value);
        inputAlias = findViewById(R.id.etx_input_alias);
        inputAliasType = findViewById(R.id.etx_input_alias_type);
        tagRemain = findViewById(R.id.tv_tag_remain);
        findViewById(R.id.btn_add_tag).setOnClickListener(this);
        findViewById(R.id.btn_delete_tag).setOnClickListener(this);
        findViewById(R.id.btn_show_tag).setOnClickListener(this);
        findViewById(R.id.btn_add_weighted_tag).setOnClickListener(this);
        findViewById(R.id.btn_delete_weighted_tag).setOnClickListener(this);
        findViewById(R.id.btn_show_weighted_tag).setOnClickListener(this);
        findViewById(R.id.btn_add_alias).setOnClickListener(this);
        findViewById(R.id.btn_delete_alias).setOnClickListener(this);
        findViewById(R.id.btn_serialnet).setOnClickListener(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_upush;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_tag:
                addTag();
                break;
            case R.id.btn_delete_tag:
                deleteTag();
                break;
            case R.id.btn_show_tag:
                showTag();
                break;
            case R.id.btn_add_weighted_tag:
                addWeightedTag();
                break;
            case R.id.btn_delete_weighted_tag:
                deleteWeightedTag();
                break;
            case R.id.btn_show_weighted_tag:
                showWeightedTag();
                break;
            case R.id.btn_add_alias:
                addAlias();
                break;
            case R.id.btn_delete_alias:
                deleteAlias();
                break;
            case R.id.btn_set_alias:
                setAlias();
                break;
            case R.id.btn_show_card_message:
                showCardMessage();
                break;
            case R.id.btn_serialnet:
                serialnet();
                break;
            case R.id.btn_device_check:
                deviceCheck();
                break;
            default:
        }
    }

    private void setAlias() {
        String alias = inputAlias.getText().toString();
        String aliasType = inputAliasType.getText().toString();
        if (TextUtils.isEmpty(alias)) {
            Toast.makeText(this, "请输入alias", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(aliasType)) {
            Toast.makeText(this, "请输入alias type", Toast.LENGTH_SHORT).show();
            return;
        }
        mPushAgent.setAlias(alias, aliasType, new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean isSuccess, String message) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        inputAlias.setText("");
                        inputAliasType.setText("");
                    }
                });
            }
        });
    }

    private void deleteAlias() {
        String alias = inputAlias.getText().toString();
        String aliasType = inputAliasType.getText().toString();
        if (TextUtils.isEmpty(alias)) {
            Toast.makeText(this, "请先输入alias", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(aliasType)) {
            Toast.makeText(this, "请先输入alias type", Toast.LENGTH_SHORT).show();
            return;
        }
        mPushAgent.deleteAlias(alias, aliasType, new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean isSuccess, String message) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        inputAlias.setText("");
                        inputAliasType.setText("");
                    }
                });
            }
        });
    }

    private void addAlias() {
        String alias = inputAlias.getText().toString();
        String aliasType = inputAliasType.getText().toString();
        if (TextUtils.isEmpty(alias)) {
            Toast.makeText(this, "请先输入alias", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(aliasType)) {
            Toast.makeText(this, "请先输入alias type", Toast.LENGTH_SHORT).show();
            return;
        }
        mPushAgent.addAlias(alias, aliasType, new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean isSuccess, String message) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        inputAlias.setText("");
                        inputAliasType.setText("");
                    }
                });
            }
        });
    }

    private void showWeightedTag() {
        mPushAgent.getTagManager().getWeightedTags(new WeightedTagListCallBack() {
            @Override
            public void onMessage(boolean isSuccess, final Hashtable<String, Integer> result) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }
        });
    }

    private void deleteWeightedTag() {
        mPushAgent.getTagManager().deleteWeightedTags(new TCallBack() {
            @Override
            public void onMessage(boolean isSuccess, final Result result) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }
        }, inputWeightedTag.getText().toString());
    }

    private void addWeightedTag() {
        Hashtable<String, Integer> table = new Hashtable<String, Integer>();
        table.put(inputWeightedTag.getText().toString(), Integer.valueOf(inputWeightedTagValue.getText().toString()));
        mPushAgent.getTagManager().addWeightedTags(new TCallBack() {
            @Override
            public void onMessage(boolean isSuccess, final Result result) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }, table);
    }

    private void showTag() {
        mPushAgent.getTagManager().getTags(new TagManager.TagListCallBack() {
            @Override
            public void onMessage(final boolean isSuccess, final List<String> result) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isSuccess) {
                            if (result != null) {
                                StringBuilder info = new StringBuilder();
                                info.append("Tags:");
                                for (int i = 0; i < result.size(); i++) {
                                    String tag = result.get(i);
                                    info.append("\n" + tag);
                                }
                            }
                        } else {
                        }

                    }
                });

            }
        });
    }

    private void deleteTag() {
        final String tag = inputTag.getText().toString();
        if (TextUtils.isEmpty(tag)) {
            Toast.makeText(this, "请先输入tag", Toast.LENGTH_SHORT).show();
            return;
        }
        mPushAgent.getTagManager().deleteTags(new TagManager.TCallBack() {
            @Override
            public void onMessage(final boolean isSuccess, final ITagManager.Result result) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        inputTag.setText("");
                        if (isSuccess) {
                            tagRemain.setText(String.valueOf(result.remain));
                            PushDialogFragment.newInstance(0, 1, tag).show(getFragmentManager(), "addtag");
                        } else {
                        }
                    }
                });
            }
        }, tag);
    }

    private void addTag() {
        final String tag = inputTag.getText().toString();
        if (TextUtils.isEmpty(tag)) {
            Toast.makeText(this, "请先输入tag", Toast.LENGTH_SHORT).show();
            return;
        }
        mPushAgent.getTagManager().addTags(new TagManager.TCallBack() {
            @Override
            public void onMessage(final boolean isSuccess, final ITagManager.Result result) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        inputTag.setText("");
                        if (isSuccess) {
                            tagRemain.setText(String.valueOf(result.remain));
                            PushDialogFragment.newInstance(0, 0, tag).show(getFragmentManager(), "addtag");
                        } else {
                        }
                    }
                });
            }
        }, tag);
    }

    private void showCardMessage() {
        InAppMessageManager.getInstance(this).showCardMessage(this, "main", new IUmengInAppMsgCloseCallback() {
            @Override
            public void onColse() {
                UmLog.i(TAG, "card message close");
            }
        });
    }

    private void serialnet() {
        DebugNotification.transmission(UpushActivity.this, handler);
    }

    private void deviceCheck() {
    }
}
