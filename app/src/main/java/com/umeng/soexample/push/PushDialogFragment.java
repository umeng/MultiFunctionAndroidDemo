package com.umeng.soexample.push;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.umeng.soexample.R;

/**
 * Created by fanyajie on 2018/1/30
 */

public class PushDialogFragment extends DialogFragment {

    static final String VIEW_TYPE = "view_type";
    static final String STATUS = "status";
    static final String TEXT = "text";

    private int viewType;
    private int status;
    private String title;
    private String text;

    static PushDialogFragment newInstance(int viewType, int status, String text) {
        PushDialogFragment fragment = new PushDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(VIEW_TYPE, viewType);
        bundle.putInt(STATUS, status);
        bundle.putString(TEXT, text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        viewType = bundle.getInt(VIEW_TYPE);
        status = bundle.getInt(STATUS);
        text = bundle.getString(TEXT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.push_dialog, container, false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
