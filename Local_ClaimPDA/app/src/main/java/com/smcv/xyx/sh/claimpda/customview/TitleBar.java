package com.smcv.xyx.sh.claimpda.customview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smcv.xyx.sh.claimpda.R;

/**
 * Created by wangxin on 09/03/2018.
 */

public class TitleBar extends LinearLayout{
    public ImageView imgLeft;
    public ImageView imgRight;
    public TextView textRight;
    public TextView textMid;
    public TextView textLeft;
    protected LinearLayout ll_title;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void init(final Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.titlebar, this, true);
        imgLeft = (ImageView) view.findViewById(R.id.img_left);
        imgRight = (ImageView) view.findViewById(R.id.img_right);
        textRight = (TextView) view.findViewById(R.id.text_right);
        textMid = (TextView) view.findViewById(R.id.text_mid);
        textLeft = (TextView) view.findViewById(R.id.text_left);
        ll_title = (LinearLayout) view.findViewById(R.id.ll_title);
        //TODO Q
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        imgLeft.setImageResource(array.getResourceId(R.styleable.TitleBar_imgLeft, 0));
        imgRight.setImageResource(array.getResourceId(R.styleable.TitleBar_imgRight, 0));
        textRight.setText(array.getString(R.styleable.TitleBar_textRight));
        textRight.setTextColor(array.getColor(R.styleable.TitleBar_textRightColor, 0xFFFFFFFF));
        textMid.setText(array.getString(R.styleable.TitleBar_textMid));
        textMid.setTextColor(array.getColor(R.styleable.TitleBar_textMidColor, 0xFFFFFFFF));
        textLeft.setText(array.getString(R.styleable.TitleBar_textLeft));
        textLeft.setTextColor(array.getColor(R.styleable.TitleBar_textLeftColor, 0xFFFFFFFF));
        ll_title.setBackground(array.getDrawable(R.styleable.TitleBar_bg));
        array.recycle();
        if (!isInEditMode()) {
            //返回按钮
            imgLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) context).finish();
                }
            });
            textLeft.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) context).finish();
                }
            });
            //  沉浸式状态栏
//            UIUtil.setImmerseLayout((Activity) context, ll_title, true);
        }

    }

    public ImageView getImgLeft() {
        return imgLeft;
    }

    public void setImgLeft(ImageView imgLeft) {
        this.imgLeft = imgLeft;
    }

    public ImageView getImgRight() {
        return imgRight;
    }

    public void setImgRight(ImageView imgRight) {
        this.imgRight = imgRight;
    }

    public TextView getTextMid() {
        return textMid;
    }

    public void setTextMid(TextView textLeft) {
        this.textMid = textMid;
    }

    public TextView getTextLeft() {
        return textLeft;
    }

    public void setTextLeft(TextView textLeft) {
        this.textLeft = textLeft;
    }

    public LinearLayout getTitleBar() {
        return ll_title;
    }

    protected void setTitleBar(LinearLayout ll_title) {
        this.ll_title = ll_title;
    }
    public TextView getTextRight() {
        return textRight;
    }

    public void setTextRight(TextView textRight) {
        this.textRight = textRight;
    }
}
