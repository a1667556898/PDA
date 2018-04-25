package com.smcv.xyx.sh.claimpda.utils.httputil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.smcv.xyx.sh.claimpda.customview.LoadingDialog;
import com.smcv.xyx.sh.claimpda.utils.LogUtil;
import com.smcv.xyx.sh.claimpda.utils.NetUtil;
import com.smcv.xyx.sh.claimpda.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * Created by SummerChen on 2018/1/15.
 */

public abstract class MyNetCallBack implements Callback {
    private Context context;
    private LoadingDialog loadingDialog;
    private String mMsg;
    public static final int SUCCESS = 0;
    public static final int TOKENFAIL = 1;
    public static final int FAIL = 2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    MyOnSuccess(msg.getData().getString("date"),mMsg);
                    break;
                case FAIL:
                    ToastUtil.getShortToast((Activity) context, mMsg,1000);
                    MyOnFailure(mMsg);
                    break;
                case TOKENFAIL:
                    MyOnFailure(mMsg);
                    ToastUtil.getShortToast((Activity)context,mMsg,1000);
                    break;
            }
        }
    };

    public MyNetCallBack(Context context) {
        this.context = context;
        loadingDialog = new LoadingDialog();
        loadingDialog.initDialog(context);
        loadingDialog.startDialog();
        if (!NetUtil.isNetworkAvailable((Activity) context)) {
            loadingDialog.stopDialog();
            ToastUtil.getShortToast((Activity) context, "网络异常,请检查网络配置",2000);
            return;
        }
    }

    public abstract void MyOnSuccess(String result,String msg);

    public abstract void MyOnFailure(String msg);

    @Override
    public void onFailure(Call call, IOException e) {
        LogUtil.i("MyNetCallBack", "请求失败：" + e.toString());
        ToastUtil.getShortToast((Activity) context, e.toString(),2000);
        loadingDialog.stopDialog();
        handler.sendEmptyMessage(FAIL);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String resBody = response.body().string().toString();
        LogUtil.i("MyNetCallBack", "请求成功结果：" + resBody);
        try {
            JSONObject json = new JSONObject(resBody);
            String code = json.getString("code");
            mMsg = json.get("msg").toString();
            LogUtil.i("MyNet", "请求成功code：" + code + ":mMsg" + mMsg);
            if (RestResponseCode.TOKEN_FAIL.equals(code+"")) {     //token失效 跳转登录界面
                handler.sendEmptyMessage(TOKENFAIL);
                return;
            } else if (RestResponseCode.SUCCESS.equals(code+"")) {   //请求成功
                String data = json.get("result").toString();
                LogUtil.i("MyNetCallBack", "请求成功结果：" + data);
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("date", data);
                message.setData(bundle);
                message.what = SUCCESS;
                handler.sendMessage(message);
            } else {     //请求失败
                handler.sendEmptyMessage(FAIL);
                return;
            }

        } catch (JSONException e) {
            e.printStackTrace();
            LogUtil.i("MyNetCallBack", "请求成功解析异常：" + resBody);
        } finally {
            loadingDialog.stopDialog();
        }
    }
}
