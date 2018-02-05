package com.umeng.soexample.analytics;

import java.util.Locale;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.umeng.analytics.MobclickAgent;
import com.umeng.soexample.BaseActivity;
import com.umeng.soexample.R;

/**
 * Created by cnzz on 18/2/2.
 *
 * <p>
 * The demo shows how to integrate analytic SDK into Application based on
 * 'Fragment'. PageView ( like Fragment or viewgroup) can be tracked with new
 * API.
 * </P>
 * <p>
 * SDKV4.6.2 之前 页面访问只能统计到 'Activity' 级别，不能统计到每个 'Fragment' .现在 新增的页面统计API，可以用来统计
 * Fragment 这样颗粒度更细的页面。
 * </p>
 */

public class UFragmentStack extends BaseActivity {

    int mStackLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("统计UApp");
        setBackVisibily();

        // Watch for button clicks.
        Button button = (Button) findViewById(R.id.new_fragment);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addFragmentToStack();
            }
        });

        if (savedInstanceState == null) {
            // Do first time initialization -- add initial fragment.
            Fragment newFragment = CountingFragment.newInstance(mStackLevel);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.simple_fragment, newFragment).commit();
        } else {
            mStackLevel = savedInstanceState.getInt("level");
        }
    }

    @Override
    public int getLayout() {
        return R.layout.umeng_example_analytics_fragment_stack;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("level", mStackLevel);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    void addFragmentToStack() {
        mStackLevel++;

        // Instantiate a new fragment.
        Fragment newFragment = CountingFragment.newInstance(mStackLevel);

        // Add the fragment to the activity, pushing this transaction
        // on to the back stack.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.simple_fragment, newFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static class CountingFragment extends Fragment {
        private String mPageName;
        int mNum;

        @Override
        public void onPause() {
            // TODO Auto-generated method stub
            super.onPause();
            MobclickAgent.onPageEnd(mPageName);
        }

        @Override
        public void onResume() {
            // TODO Auto-generated method stub
            super.onResume();
            MobclickAgent.onPageStart(mPageName);
        }

        /**
         * Create a new instance of CountingFragment, providing "num" as an
         * argument.
         */
        static CountingFragment newInstance(int num) {
            CountingFragment f = new CountingFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            return f;
        }

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
            mPageName = String.format(Locale.getDefault(),"fragment %d", mNum);
        }

        /**
         * The Fragment's UI is just a simple text view showing its instance
         * number.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            FrameLayout fl = new FrameLayout(getActivity());
            fl.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
            fl.setBackgroundColor(getActivity().getResources().getColor(R.color.bg));
            TextView tv = new TextView(getActivity());
            tv.setText("Fragment #" + mNum);
            tv.setTextColor(Color.BLACK);
            fl.addView(tv);
            return fl;
        }
    }


}
