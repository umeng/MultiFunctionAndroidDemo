package com.umeng.soexample.log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.umeng.soexample.R;
import com.umeng.soexample.log.bean.UpdateLog;
import com.umeng.soexample.log.views.LogAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wangfei on 2018/1/22.
 */

public abstract class BaseFragment  extends Fragment {
    ListView listView;
    LogAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log,container,false);
        listView = (ListView) view.findViewById(R.id.list);
        initData();
        return view;
    }

    /**
     * @return
     */
    public abstract String getFileName();
    private void initData(){
        String fileName = getFileName();
        String logStr = readLog(fileName);
        ArrayList<UpdateLog> list = new ArrayList<UpdateLog>();
        try {
            JSONArray array = new JSONArray(logStr);
            for (int i = 0 ;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                UpdateLog log = new UpdateLog();
                log.name = jsonObject.optString("name");
                log.date = jsonObject.optString("date");
                log.content = jsonObject.optString("content");
                list.add(log);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (list.size()>0){
            adapter = new LogAdapter(this.getActivity(),list);
            listView.setAdapter(adapter);
        }
    }
    private String readLog(String fileName){
        try {
            InputStream is = getActivity().getAssets().open(fileName+".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String text = new String(buffer, "utf-8");
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}