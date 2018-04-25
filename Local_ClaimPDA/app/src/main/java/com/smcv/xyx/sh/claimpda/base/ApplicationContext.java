package com.smcv.xyx.sh.claimpda.base;

import android.support.multidex.MultiDexApplication;

/**
 * Created by wangxin on 09/04/2018.
 */

public class ApplicationContext extends MultiDexApplication {
    private static ApplicationContext mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    public static ApplicationContext getInstance(){
        return mInstance;
    }
}
