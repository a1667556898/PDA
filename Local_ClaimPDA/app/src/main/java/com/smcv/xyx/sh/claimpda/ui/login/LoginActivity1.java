package com.smcv.xyx.sh.claimpda.ui.login;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.smcv.xyx.sh.claimpda.R;
import com.smcv.xyx.sh.claimpda.api.AppContact;
import com.smcv.xyx.sh.claimpda.base.BaseActivity;
import com.smcv.xyx.sh.claimpda.base.BaseApplication;
import com.smcv.xyx.sh.claimpda.base.SharePreferenceKey;
import com.smcv.xyx.sh.claimpda.ui.MainActivity;
import com.smcv.xyx.sh.claimpda.utils.ActivityManger;
import com.smcv.xyx.sh.claimpda.utils.LogUtil;
import com.smcv.xyx.sh.claimpda.utils.SharedPreferencesUtil;
import com.smcv.xyx.sh.claimpda.utils.StringUtil;
import com.smcv.xyx.sh.claimpda.utils.WebViewUtil;

import butterknife.BindView;


/**
 * Created by ${SummerChen} on 2018/1/15.
 */

public class LoginActivity1 extends BaseActivity {
    @BindView(R.id.myWeb)
    WebView myWeb;
    private boolean isFromExit = false;//是否从退出进来的
    private boolean isFirst = true;//是否是第一次加载登录 退出进来的时候要判断

    @Override
    public int getLayoutId() {
        return R.layout.activity_login1;
    }

    @Override
    protected void initView() {
        super.initView();
        isFromExit = getIntent().getBooleanExtra("isFromExit", false);
        WebViewUtil.initWeb(myWeb);

        myWeb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                LogUtil.i(Tag, "WebViewClient:" + url);
                if (!StringUtil.isEmpty(url) && url.startsWith(AppContact.XIEYI)) {
                    String token = url.substring(20, url.length());
                    LogUtil.i(Tag, token);
                    SharedPreferencesUtil.getInstance(LoginActivity1.this).putKVP(SharePreferenceKey.TOKEN, token);
                    ActivityManger.goToMainActivity(LoginActivity1.this, MainActivity.STATE_DEFAULT);
                    finish();
                }

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                LogUtil.i(Tag, "onPageFinished:" + url);
                if (isFromExit && isFirst) {
                    myWeb.loadUrl(AppContact.LOADURL);
                    isFirst = false;
                }
            }
        });
        if (isFromExit) {
            myWeb.loadUrl(AppContact.EXITURL);
        } else {
            myWeb.loadUrl(AppContact.LOADURL);
        }


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        BaseApplication.getInstance().destroy();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != myWeb) {
            ViewGroup parent = (ViewGroup) myWeb.getParent();
            if (parent != null) {
                parent.removeView(myWeb);
            }
            myWeb.removeAllViews();
            myWeb.destroy();
        }
    }
}
