package com.smcv.xyx.sh.claimpda.ui.chuku;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.smcv.xyx.sh.claimpda.R;
import com.smcv.xyx.sh.claimpda.api.AppContact;
import com.smcv.xyx.sh.claimpda.base.BaseActivity;
import com.smcv.xyx.sh.claimpda.base.SharePreferenceKey;
import com.smcv.xyx.sh.claimpda.customview.MyListView;
import com.smcv.xyx.sh.claimpda.model.ChuKuNewBean;
import com.smcv.xyx.sh.claimpda.utils.ActivityManger;
import com.smcv.xyx.sh.claimpda.utils.LogUtil;
import com.smcv.xyx.sh.claimpda.utils.SharedPreferencesUtil;
import com.smcv.xyx.sh.claimpda.utils.StringUtil;
import com.smcv.xyx.sh.claimpda.utils.httputil.MyHttpUtil;
import com.smcv.xyx.sh.claimpda.utils.httputil.MyNetCallBack;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wangxin on 15/03/2018.
 */

public class ChuKuConfirmSecondActivity extends BaseActivity {
    @BindView(R.id.tv_afterSale_code)
    TextView tvAfterSaleCode;
    @BindView(R.id.et_lingliao_person)
    EditText etLingliaoPeople;
    @BindView(R.id.et_lingliao_part)
    EditText etLingliaoPart;
    @BindView(R.id.myListView)
    MyListView myListView;
    @BindView(R.id.tv_btn_exit)
    TextView tvBtnExit;
    @BindView(R.id.tv_btn_confirm)
    TextView tvBtnConfirm;

    private ArrayList<ChuKuNewBean> chuKuNewBeens = new ArrayList<>();
    private int StateTag;
    private ChuKuConfirmAdapter chuKuConfirmAdapter;
    private String chukuDate = "";//出库日期
    private String lingliaoDepartment = "";//领料部门
    private String lingliaoPerson = "";//领料人
    private String userId="";
    @Override
    public int getLayoutId() {
        return R.layout.activity_chuku_confirm_sec;
    }

    @Override
    protected void initView() {
        super.initView();
        userId= SharedPreferencesUtil.getInstance(ChuKuConfirmSecondActivity.this).getValueByKeyString(SharePreferenceKey.USERID,"");

        initData(getIntent());
    }

    /**
     * 初始化数据
     *
     * @param intent
     */
    private void initData(Intent intent) {
        Bundle bundle = intent.getExtras();
        chuKuNewBeens = (ArrayList<ChuKuNewBean>) bundle.getSerializable("chuKuNewBeens");
        StateTag = bundle.getInt("StateTag");
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        chukuDate=df.format(day);
        tvAfterSaleCode.setText(chukuDate);
        chuKuConfirmAdapter = new ChuKuConfirmAdapter(ChuKuConfirmSecondActivity.this, chuKuNewBeens);
        myListView.setAdapter(chuKuConfirmAdapter);
    }

    @Override
    protected void listener() {
        super.listener();
        etLingliaoPeople.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                lingliaoPerson = s.toString();

            }
        });
        etLingliaoPart.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                lingliaoDepartment = s.toString();
            }
        });
    }

    @OnClick({R.id.tv_btn_exit, R.id.tv_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_exit:
                ActivityManger.goToMainActivity(ChuKuConfirmSecondActivity.this, -1);
                break;
            case R.id.tv_btn_confirm:
                if (StringUtil.isEmpty(lingliaoDepartment)) {
                    showToast("请输入领料部门");
                    return;
                } else if (StringUtil.isEmpty(lingliaoPerson)) {
                    showToast("请输入领料人");
                    return;
                } else {
                    updata2Server();
                }
                break;
        }
    }

    /**
     * 创建出库单 上传数据到后台
     */
    private void updata2Server() {
        //TODO 数据的封装
        TreeMap treeMap = new TreeMap();
        treeMap.put("outStoragePickPeople", lingliaoPerson);//领料人
        treeMap.put("outStoragePickDepartment", lingliaoDepartment);//领料部门
        treeMap.put("outStorageDate", chukuDate);//出库日期
        treeMap.put("partsList", chuKuNewBeens);
        treeMap.put("userId", userId);
        LogUtil.i(Tag,treeMap.toString());
        MyHttpUtil.myHttpPost(AppContact.CONFIRMCHUKU, treeMap, new MyNetCallBack(this) {
            @Override
            public void MyOnSuccess(String result, String msg) {
                showToast(msg);
                ActivityManger.goToMainActivity(ChuKuConfirmSecondActivity.this, -1);
            }

            @Override
            public void MyOnFailure(String msg) {
                showToast(msg);
            }
        });
    }
}
