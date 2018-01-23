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
 * Created by wangfei on 2018/1/22.
 */

public class Function extends RelativeLayout {

    private TextView mTextView;
    private TextView descTv;

    private String titleText;
    private String desc;


    public Function(Context context) {
        super(context);
    }

    public Function(Context context, AttributeSet attrs) {
        super(context, attrs);
        //
        ////加载视图的布局
        LayoutInflater.from(context).inflate(R.layout.function,this,true);
        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.Function);
        if (TextUtils.isEmpty(titleText)){
            titleText=a.getString(R.styleable.Function_umfuncname);
        }
        if (TextUtils.isEmpty(desc)){
           desc=a.getString(R.styleable.Function_umfuncdesc);
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
        mTextView= (TextView) findViewById(R.id.func_name);
        descTv= (TextView) findViewById(R.id.func_desc);
        mTextView.setText(titleText);
        descTv.setText(desc);


    }


    public void setName(String text) {
        if (mTextView!=null){
            mTextView.setText(text);
        }
        titleText = text;
    }


    public void setDesc(String text) {
        if (descTv!=null){
            descTv.setText(text);
        }
        desc = text;
    }

}