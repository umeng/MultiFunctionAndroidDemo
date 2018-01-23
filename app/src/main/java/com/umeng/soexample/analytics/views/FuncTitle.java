package com.umeng.soexample.analytics.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.umeng.soexample.R;

/**
 * Created by wangfei on 2018/1/23.
 */

public class FuncTitle extends RelativeLayout {


    private TextView mTextView;


    private String titleText;



    public FuncTitle(Context context) {
        super(context);
    }

    public FuncTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        //
        ////加载视图的布局
        LayoutInflater.from(context).inflate(R.layout.functitle,this,true);
        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.FunctionTitle);
        if (TextUtils.isEmpty(titleText)){
            titleText=a.getString(R.styleable.FunctionTitle_umfunctitle);
        }


        a.recycle();
    }

    /**
     * 此方法会在所有的控件都从xml文件中加载完成后调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //获取子控件
        mTextView= (TextView) findViewById(R.id.item_title);

        mTextView.setText(titleText);



    }


    public void setName(String text) {
        if (mTextView!=null){
            mTextView.setText(text);
        }
        titleText = text;
    }



}