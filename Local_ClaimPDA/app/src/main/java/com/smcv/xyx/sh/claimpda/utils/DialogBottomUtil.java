package com.smcv.xyx.sh.claimpda.utils;


import android.app.Dialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.smcv.xyx.sh.claimpda.R;

import static android.R.attr.width;


/**
 * Created by wangxin on 2017/7/11.
 */

public class DialogBottomUtil {
    public static void applyDialogBottom(Dialog dialog) {
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
//        int width = App.getInstance().getResources().getDisplayMetrics().widthPixels;
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.windowAnimations = R.style.DialogAnimation;  //动画
        lp.width = width;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
    }
}
