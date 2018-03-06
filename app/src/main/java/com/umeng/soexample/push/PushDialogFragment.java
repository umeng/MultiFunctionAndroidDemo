package com.umeng.soexample.push;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.soexample.R;

public class PushDialogFragment extends DialogFragment {

    static final String VIEW_TYPE = "view_type";
    static final String STATUS = "status";
    static final String TITLE = "title";
    static final String TEXT = "text";

    private int viewType;
    private int status;
    private String title;
    private String text;

    static PushDialogFragment newInstance(int viewType, int status, String title, String text) {
        PushDialogFragment fragment = new PushDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(VIEW_TYPE, viewType);
        bundle.putInt(STATUS, status);
        bundle.putString(TITLE, title);
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
        title = bundle.getString(TITLE);
        text = bundle.getString(TEXT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = null;
        if (viewType == 0) {
            v = inflater.inflate(R.layout.push_dialog, container, false);
            ImageView ivIcon = v.findViewById(R.id.push_dialog_icon);
            TextView tvTitle = v.findViewById(R.id.push_dialog_title);
            TextView tvText = v.findViewById(R.id.push_dialog_text);
            tvTitle.setText(title);
            tvText.setText(getString(R.string.push_tag) + text);


            if (status == 0) {
                ivIcon.setImageResource(R.drawable.um_push_tip);
            }

            if (status == 1) {
                ivIcon.setImageResource(R.drawable.um_push_ok);
            }
        }

        if (viewType == 1) {
            v = inflater.inflate(R.layout.push_dialog_result, container, false);
            TextView tl = v.findViewById(R.id.push_dialog_title);
            TextView tx = v.findViewById(R.id.push_dialog_text);
            tl.setText(title);
            tx.setText(text);

        }

        if (v != null) {
            Button btnOK = v.findViewById(R.id.btn_ok);
            btnOK.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    PushDialogFragment.this.getDialog().cancel();
                }
            });
        }

        return v;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
