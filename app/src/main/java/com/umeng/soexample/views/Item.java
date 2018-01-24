package com.umeng.soexample.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.umeng.soexample.R;

/**
 * Created by wangfei on 2018/1/22.
 */

public class Item extends RelativeLayout {

    private TextView mTextView;
    private ImageView imageView;

    private String titleText;
    private int iconId;


    public Item(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item,this,true);
        mTextView= (TextView) findViewById(R.id.item_title);
        imageView= (ImageView) findViewById(R.id.item_icon);
    }

    public Item(Context context, AttributeSet attrs) {
        super(context, attrs);
        //
        ////加载视图的布局
        LayoutInflater.from(context).inflate(R.layout.item,this,true);
        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.Item);
        if (TextUtils.isEmpty(titleText)){
            titleText=a.getString(R.styleable.Item_umtitle);
        }
       if (iconId == 0){
           iconId=a.getResourceId(R.styleable.Item_umicon, 0);
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
        imageView= (ImageView) findViewById(R.id.item_icon);
        mTextView.setText(titleText);
        if (iconId<=0){
            imageView.setVisibility(GONE);
        }else {
            imageView.setImageResource(iconId);
        }

    }


    public void setName(String text) {
        if (mTextView!=null){
            mTextView.setText(text);
        }
        titleText = text;
    }
    public void setNoIcon() {
        if (imageView!=null){
            imageView.setVisibility(GONE);
        }
    }

    public void setIcon(int id) {
        if (imageView!=null){
        imageView.setImageResource(id);
        }
        iconId = id;
    }

}