package com.umeng.soexample.analytics;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.dplus.UMADplus;
import com.umeng.analytics.game.UMGameAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.soexample.BaseActivity;
import com.umeng.soexample.R;

/**
 * Created by wangfei on 2018/1/23.
 */

public class UGameActivity extends BaseActivity {

    private Context mContext;
    private String level = "level-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("统计UGame");
        setBackVisibily();

        mContext = this;
        // 设置输出运行时日志
        UMConfigure.setLogEnabled(true);
        UMGameAgent.init(this);
        // Deprecated UMGameAgent.setPlayerLevel("LV.01");
        UMGameAgent.setPlayerLevel(1);
        //UMGameAgent.setSessionContinueMillis(1000);

        // 设置U-DPlus + 高级GAME场景
        UMGameAgent.setScenarioType(mContext, MobclickAgent.EScenarioType.E_DUM_GAME);

        findViewById(R.id.analytics_g1_b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 关卡开始
                UMGameAgent.startLevel(level);
                Toast.makeText(mContext, "已完成关卡开始", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.analytics_g1_b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 关卡失败
                UMGameAgent.failLevel(level);
                Toast.makeText(mContext, "已完成关卡失败", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.analytics_g1_b3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 成功过关
                UMGameAgent.finishLevel(level);
                Toast.makeText(mContext, "已完成成功过关", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.analytics_g2_b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 使用支付宝 10 元购买 2000 个虚拟币
                UMGameAgent.pay(10, 2000, PayChannels.ZHI_FU_BAO);
                Toast.makeText(mContext, "已完成充值付费（真实消费）", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.analytics_g2_b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 使用支付宝 10 元购买一把宝剑 (等值虚拟币 1000)
                UMGameAgent.pay(10, "sword", 1, 1000, PayChannels.ZHI_FU_BAO);
                Toast.makeText(mContext, "已完成购买物品（真实消费）", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.analytics_g2_b3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 用支付宝 使用了88.88美元(等同于10000个虚拟币) 订单ID是test-ordedid
                UMGameAgent.exchange(88.88, "USD", 10000, PayChannels.ZHI_FU_BAO, "test-ordedid");
                Toast.makeText(mContext, "已完成有订单的充值付费（真实消费）", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.analytics_g3_b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 购买 2 个魔法药水（每个虚拟币单价100）
                UMGameAgent.buy("magic", 2, 100);
                Toast.makeText(mContext, "已完成购买物品（虚拟消费）", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.analytics_g4_b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 使用 1 个魔法药水（每个虚拟币单价100）
                UMGameAgent.use("magic", 1, 100);
                Toast.makeText(mContext, "已完成使用物品", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.analytics_g5_b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 开发商赠送 1000 个虚拟币
                UMGameAgent.bonus(1000, BonusTrigger.KAI_FA_SHANG_ZENG_SONG);
                // 玩家赠送一把宝剑价值 100 个虚拟币
                UMGameAgent.bonus("sword", 1, 100, BonusTrigger.WAN_JIA_ZENG_SONG);
                Toast.makeText(mContext, "已完成游戏奖励", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.analytics_g6_b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 用户登录
                UMGameAgent.onProfileSignIn("example_id");
                Toast.makeText(mContext, "已完成用户登录", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.analytics_g6_b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 用户退出
                UMGameAgent.onProfileSignOff();
                Toast.makeText(mContext, "已完成用户退出", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 1-8 为默认支付渠道, 9 ~ 20 保留字段， 21~ 99 为自定义渠道
     *
     * @author ntop
     */
    @SuppressWarnings("unused")
    private interface PayChannels {
        static int APPSTORE = 1; // APPSTORE
        static int ZHI_FU_BAO = 2; // 支付宝(1),
        static int WANG_YIN = 3; // 网银
        static int CAI_FU_TONG = 4;// 财付通(3),
        static int YI_DONG = 5; // 移动(4)
        static int LIAN_TONG = 6; // 联通通信(5),
        static int DIAN_XIN = 7; // 电信
        static int PAYPAL = 8; // PAYPAL

        static int WANDOUJIA = 21;// 自定义支付渠道，豌豆荚支付
    }

    /**
     * <p>
     * 1-3 为默认的游戏奖励触发点
     * </p>
     * <p>
     * 4-20 保留字段
     * </p>
     * <p>
     * 21-99 为自定义触发点（需要在网站上也做出相应的配置）
     * </p>
     *
     * @author ntop
     */
    @SuppressWarnings("unused")
    private interface BonusTrigger {
        static int WAN_JIA_ZENG_SONG = 1;// 玩家赠送(1),
        static int KAI_FA_SHANG_ZENG_SONG = 2;// 开发商赠送(2)
        static int YOU_XI_JIANG_LI = 3;// 游戏奖励(3)

        static int FROM_BOSS = 21;// 自定义触发点，boss 掉落
    }

    @Override
    public int getLayout() {
        return R.layout.activity_ugame;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 集成基本统计分析,初始化 Session
        // MobclickAgent.onResume(this); // BaseActivity中已经统一调用，此处无需再调用
    }

    @Override
    public void onPause() {
        super.onPause();
        // 集成基本统计分析, 结束 Session
        // MobclickAgent.onPause(this); // BaseActivity中已经统一调用，此处无需再调用

    }
}
