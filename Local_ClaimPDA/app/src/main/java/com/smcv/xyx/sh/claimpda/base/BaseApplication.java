package com.smcv.xyx.sh.claimpda.base;

import android.app.Activity;
import android.app.Application;

import com.smcv.xyx.sh.claimpda.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${SummerChen} on 2018/1/12.
 */

public class BaseApplication extends Application {
    public final String TAG = getClass().getSimpleName();
    public static BaseApplication instance;
    public static List<Object> activitys = new ArrayList<Object>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //SharedPreferences初始化
        initSharedPrefences();
        //网络请求初始化
        initNetRequest();
        //图片加载初始化
        initGlide();
    }

    /**
     * 单例，返回一个实例
     *
     * @return
     */
    public static BaseApplication getInstance() {
        if (instance == null) {


        }
        return instance;
    }



    private void initGlide() {

    }

    private void initNetRequest() {

    }

    private void initSharedPrefences() {
        SharedPreferencesUtil.getInstance(this);
    }


    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if (!activitys.contains(activity))
            activitys.add(activity);
    }

    // 遍历所有Activity并finish
    public void destroy() {
        for (Object activity : activitys) {
            ((Activity) activity).finish();
        }
    }
}
