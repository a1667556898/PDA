package com.smcv.xyx.sh.claimpda.utils.httputil;


import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Created by SummerChen on 2018/1/16.
 */

public class SignOkHttpClient extends OkHttpClient {
    private volatile static SignOkHttpClient singleton;  //静态变量

    private SignOkHttpClient() {
    }  //私有构造函数

    public static SignOkHttpClient getInstance() {
        if (singleton == null) {  //第一层校验
            synchronized (SignOkHttpClient.class) {
                if (singleton == null) {  //第二层校验
                    singleton = new SignOkHttpClient();
                    singleton.newBuilder()
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(30, TimeUnit.SECONDS)
                            .hostnameVerifier(new HostnameVerifier() {
                                @Override
                                public boolean verify(String s, SSLSession sslSession) {
                                    return true;
                                }
                            })
                            .build();
                }
            }
        }
        return singleton;
    }

}
