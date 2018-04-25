package com.smcv.xyx.sh.claimpda.customview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.smcv.xyx.sh.claimpda.R;


/**
 * Created by Jack.chen on 2017/6/21 0021.
 * Email:1667556898@qq.com
 */

public class CustomerDialog {
    private TextView tv_title;
    private TextView tv_ok;
    private TextView tv_cancle;
    private MyOkOnClick myOkOnClick;
    private MyCancleOnClick myCancleOnClick;
private TextView tv_currentkuwei;
    public interface MyOkOnClick {
        void myokonclick();
    }
    public interface MyCancleOnClick {
        void myCancleOnClick();
    }

    public void setMyOkOnClick(MyOkOnClick myOkOnClick) {
        this.myOkOnClick = myOkOnClick;

    }
    public void setMyCancleOnClick(MyCancleOnClick myCancleOnClick){
        this.myCancleOnClick = myCancleOnClick;
    }
    public void showSelectDialog(final Context mcontext, String content, String cancle, String ok) {
        View view = View.inflate(mcontext, R.layout.dialogcustom, null);
        tv_currentkuwei = (TextView) view.findViewById(R.id.tv_currentkuwei);
        tv_ok = (TextView) view.findViewById(R.id.tv_ok);
        tv_cancle = (TextView) view.findViewById(R.id.tv_dismiss);
        tv_currentkuwei.setText(content);
        tv_ok.setText(ok);
        tv_cancle.setText(cancle);
        final Dialog dialog = new Dialog(mcontext, R.style.customDialog);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);//设置基本位置
        lp.x = 0;
        lp.y = 0;
//        lp.alpha=0.5f;
        window.setAttributes(lp);//设置偏移位置 透明度等
        window.setBackgroundDrawable(new BitmapDrawable());
        dialog.show();
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (null != myCancleOnClick) {
                    myCancleOnClick.myCancleOnClick();
                }
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (null != myOkOnClick) {
                    myOkOnClick.myokonclick();
                }
            }
        });
    }
}
