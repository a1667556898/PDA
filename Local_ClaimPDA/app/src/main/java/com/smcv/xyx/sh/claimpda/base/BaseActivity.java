package com.smcv.xyx.sh.claimpda.base;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.smcv.xyx.sh.claimpda.utils.ToastUtil;

import butterknife.ButterKnife;

/**
 * Created by ${SummerChen} on 2018/1/12.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected final String Tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        BaseApplication.getInstance().addActivity(this);
        initView();
        loadData();
        listener();
    }

    /**
     * 检查是否登录
     */
    public boolean isLogin(boolean isTo) {
        if ("account" == null) {
            if (isTo) {
//                Intent intent = new Intent(this, LoginActivity.class);
//                startActivityForResult(intent, 100);
            } else
                return false;
            return false;
        } else {
            return true;
        }
    }

    protected void loadData() {
    }

    protected void initView() {

    }

    protected void listener() {
    }

    public abstract int getLayoutId();

    /**
     * toast 中心位置
     *
     * @param toast
     */
    public void showToast(String toast) {
        ToastUtil.getShortToast(this, toast, 1000);
    }
}
