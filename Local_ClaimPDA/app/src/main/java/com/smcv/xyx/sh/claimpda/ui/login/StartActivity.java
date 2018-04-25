package com.smcv.xyx.sh.claimpda.ui.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

import com.smcv.xyx.sh.claimpda.R;
import com.smcv.xyx.sh.claimpda.base.BaseActivity;
import com.smcv.xyx.sh.claimpda.utils.ActivityManger;
import com.smcv.xyx.sh.claimpda.utils.UIUtil;

import butterknife.BindView;

/**
 * Created by wangxin on 2018/1/23.
 */

public class StartActivity extends BaseActivity {


    @BindView(R.id.iv_start)
    ImageView ivStart;

    @Override
    public int getLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtil.setImmerseLayout(StartActivity.this,ivStart,false);
        StartUpCountDownTimer countDownTimer = new StartUpCountDownTimer(1000 * 1, 1000);//一个总时长，一个间隔时长
        countDownTimer.start();
    }

    class StartUpCountDownTimer extends CountDownTimer {

        public StartUpCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
//            ActivityManger.goToMainActivity(StartActivity.this, 0);
            ActivityManger.goToLoginActivity1(StartActivity.this, false);
            finish();
        }
    }
}
