package com.umeng.soexample.log.views;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.umeng.soexample.R;
import com.umeng.soexample.log.bean.UpdateLog;

/**
 * Created by wangfei on 2018/1/22.
 */

public class LogAdapter extends BaseAdapter{
    private List<UpdateLog> list;
    private Context context;
    public LogAdapter(Context context, List<UpdateLog> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_item, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView)convertView.findViewById(R.id.name);
            viewHolder.content = (TextView)convertView.findViewById(R.id.content);
            viewHolder.date = (TextView)convertView.findViewById(R.id.date);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.name.setText(list.get(i).name);
        viewHolder.date.setText("更新日期:"+list.get(i).date);
        viewHolder.content.setText(list.get(i).content);

        return convertView;
    }
    static class ViewHolder{
        TextView name;
        TextView content;
        TextView date;
    }
}
