package com.umeng.soexample.log;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.umeng.soexample.R;
import com.umeng.soexample.log.views.LogFragmentPagerAdapter;
import com.umeng.soexample.views.Tab;

/**
 * Created by wangfei on 2018/1/22.
 */

public class LogActivity extends FragmentActivity{
    Tab uapp,upush,ushare;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        uapp = (Tab)findViewById(R.id.uapp);
        upush = (Tab)findViewById(R.id.upush);
        ushare = (Tab)findViewById(R.id.ushare);

         viewPager = (ViewPager) findViewById(R.id.viewPager);
        ArrayList<BaseFragment> fragments = new ArrayList<BaseFragment>();
        fragments.add(new UAPPLogFrg());
        fragments.add(new UPushLogFrg());
        fragments.add(new UShareLogFrg());
        LogFragmentPagerAdapter adapter = new LogFragmentPagerAdapter(getSupportFragmentManager(),
            fragments, this);
        viewPager.setAdapter(adapter);
        uapp.setTabSelected();
        uapp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                uapp.setTabSelected();
                upush.setTabUnSelected();
                ushare.setTabUnSelected();
                viewPager.setCurrentItem(0);
            }
        });
        upush.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                upush.setTabSelected();
                uapp.setTabUnSelected();
                ushare.setTabUnSelected();
                viewPager.setCurrentItem(1);
            }
        });
        ushare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ushare.setTabSelected();
                upush.setTabUnSelected();
                uapp.setTabUnSelected();
                viewPager.setCurrentItem(2);
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               switch (position){
                   case 0:
                       uapp.setTabSelected();
                       upush.setTabUnSelected();
                       ushare.setTabUnSelected();
                       break;
                   case 1:
                       upush.setTabSelected();
                       uapp.setTabUnSelected();
                       ushare.setTabUnSelected();
                       break;
                   case 2:
                       ushare.setTabSelected();
                       upush.setTabUnSelected();
                       uapp.setTabUnSelected();
                       break;
                   default:
                       break;
               }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setTitle("更新日志");
        setBackVisibily();
    }
    public void setTitle(String title){
        ((TextView)findViewById(R.id.umeng_title)).setText(title);
    }
    public void setBackVisibily(){
        findViewById(R.id.umeng_back).setVisibility(View.VISIBLE);
        findViewById(R.id.umeng_back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                clickBack(view);
            }
        });
    }
    public void clickBack(View view){
        finish();
    }
}
