package com.umeng.soexample.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.umeng.soexample.R;

/**
 * Created by wangfei on 2018/1/22.
 */

public class Tab extends RelativeLayout {

    private TextView mTextView;

    private String titleText;
    private int iconId;

    private Context context;
    public Tab(Context context) {
        super(context);
        this.context = context;
    }

    public Tab(Context context, AttributeSet attrs) {
        super(context, attrs);
        //
        ////加载视图的布局
        LayoutInflater.from(context).inflate(R.layout.tab,this,true);
        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.Tab);
        titleText=a.getString(R.styleable.Tab_umname);

        a.recycle();
    }

    /**
     * 此方法会在所有的控件都从xml文件中加载完成后调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //获取子控件
        mTextView= (TextView) findViewById(R.id.name);
        mTextView.setText(titleText);

    }
    public void setTabSelected(){
        mTextView.setTextColor(0xff2196F3);
        findViewById(R.id.selected).setVisibility(VISIBLE);
    }
    public void setTabUnSelected(){
        mTextView.setTextColor(0xff333333);
        findViewById(R.id.selected).setVisibility(GONE);
    }

    public void setText(String text) {
        mTextView.setText(text);
    }




}