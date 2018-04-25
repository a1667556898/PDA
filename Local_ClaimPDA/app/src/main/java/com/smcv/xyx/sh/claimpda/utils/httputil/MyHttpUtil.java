package com.smcv.xyx.sh.claimpda.utils.httputil;

import com.google.gson.Gson;
import com.smcv.xyx.sh.claimpda.base.BaseApplication;
import com.smcv.xyx.sh.claimpda.base.SharePreferenceKey;
import com.smcv.xyx.sh.claimpda.utils.LogUtil;
import com.smcv.xyx.sh.claimpda.utils.SharedPreferencesUtil;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * Created by SummerChen on 2018/1/15.
 */

public class MyHttpUtil {
    /**
     * get请求
     *
     * @param url
     * @param callBack
     */
    public static void myHttpGet(String url, MyNetCallBack callBack) {
        String token= SharedPreferencesUtil.getInstance(BaseApplication.getInstance()).getValueByKeyString(SharePreferenceKey.TOKEN,"");
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("xyx_jwt", token)
                .build();
        SignOkHttpClient.getInstance().newCall(request).enqueue(callBack);
    }

    /**
     * post请求
     *
     * @param url
     * @param params
     * @param callBack
     */
    public static void myHttpPost(String url, Map<String, String> params, MyNetCallBack callBack) {
        LogUtil.i("MyHttpUtil", "请求参数：" + params.toString());
        String token= SharedPreferencesUtil.getInstance(BaseApplication.getInstance()).getValueByKeyString(SharePreferenceKey.TOKEN,"");
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(params));
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("xyx_jwt", token)
                .post(body)
                .build();
        SignOkHttpClient.getInstance().newCall(request).enqueue(callBack);

    }

}
