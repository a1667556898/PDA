package com.smcv.xyx.sh.claimpda.utils.httputil;


import com.google.gson.Gson;
import com.smcv.xyx.sh.claimpda.base.BaseApplication;
import com.smcv.xyx.sh.claimpda.base.SharePreferenceKey;
import com.smcv.xyx.sh.claimpda.utils.MD5Util;
import com.smcv.xyx.sh.claimpda.utils.SharedPreferencesUtil;
import com.smcv.xyx.sh.claimpda.utils.StringUtil;

import java.util.TreeMap;


/**
 * @author hailong .
 *         Create on 2017/8/9
 */

public class PostData extends TreeMap {
    private static final long serialVersionUID = -3918611306392239969L;
    private TreeMap<String, String> map = new TreeMap<>();

    public PostData() {
        //time为以秒为单位
        put("time", String.valueOf(System.currentTimeMillis() / 1000));
        //TODO token 传递
        String token = SharedPreferencesUtil.getInstance(BaseApplication.getInstance()).getValueByKeyString(SharePreferenceKey.TOKEN, "");
        if (StringUtil.isEmpty(token)) {
            token = "test";
        }
        put("token", token);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * 封装好的SignMap
     *
     * @return
     */
    public void post() {
        //map.put("userId", "1");
//        put("userId", UserUtils.getUserBean(ApplicationContext.getInstance()).getUserId());
        put("reqData", new TreeMap<>(map));
        put("sign", getSign());
    }


    /**
     * 传递参数
     *
     * @param key
     * @param value
     */
    public void push(Object key, Object value) {
        map.put(key.toString(), value.toString());
    }

    /**
     * 对Sign进行md5加密
     *
     * @return 获取签名
     */
    private String getSign() {

        String baseSignMsg = "reqData=" + new Gson().toJson(map) + "&time=" + String.valueOf(get("time"))
                + "&token=" + String.valueOf(get("token"));
        String sign = MD5Util.getMD5(baseSignMsg);

        return sign;
    }
}

