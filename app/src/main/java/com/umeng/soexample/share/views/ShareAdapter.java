package com.umeng.soexample.share.views;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.socialize.common.ResContainer;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.soexample.R;

/**
 * Created by wangfei on 16/7/12.
 */
public class ShareAdapter extends BaseAdapter{
    private ArrayList<SnsPlatform> list;
    private Context context;
    public ShareAdapter(Context context, ArrayList<SnsPlatform> list){
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView ==null){
            convertView =  LayoutInflater.from(context).inflate(R.layout.app_shareadapter_share, null);
        }
        ImageView img = (ImageView) convertView.findViewById(R.id.adapter_image);
        img.setImageResource(ResContainer.getResourceId(context,"drawable",list.get(position).mIcon));
        TextView tv = (TextView)convertView.findViewById(R.id.name);
        tv.setText(list.get(position).mShowWord);
        if (position == list.size()-1){
            convertView.findViewById(R.id.divider).setVisibility(View.GONE);
        }else {
            convertView.findViewById(R.id.divider).setVisibility(View.VISIBLE);
        }
        return convertView;
    }
}
