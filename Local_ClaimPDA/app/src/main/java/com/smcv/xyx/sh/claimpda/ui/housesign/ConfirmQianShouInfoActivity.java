package com.smcv.xyx.sh.claimpda.ui.housesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smcv.xyx.sh.claimpda.R;
import com.smcv.xyx.sh.claimpda.api.AppContact;
import com.smcv.xyx.sh.claimpda.base.BaseActivity;
import com.smcv.xyx.sh.claimpda.base.SharePreferenceKey;
import com.smcv.xyx.sh.claimpda.customview.TitleBar;
import com.smcv.xyx.sh.claimpda.model.QianShouBean;
import com.smcv.xyx.sh.claimpda.ui.MainActivity;
import com.smcv.xyx.sh.claimpda.utils.ActivityManger;
import com.smcv.xyx.sh.claimpda.utils.SharedPreferencesUtil;
import com.smcv.xyx.sh.claimpda.utils.httputil.MyHttpUtil;
import com.smcv.xyx.sh.claimpda.utils.httputil.MyNetCallBack;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wangxin on 15/03/2018.
 */

public class ConfirmQianShouInfoActivity extends BaseActivity {
    @BindView(R.id.main_title_bar)
    TitleBar mainTitleBar;
    @BindView(R.id.tv_afterSale_code)
    TextView tvAfterSaleCode;
    @BindView(R.id.tv_afterSale_name)
    TextView tvAfterSaleName;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_part_num)
    TextView tvPartNum;
    @BindView(R.id.tv_btn_exit)
    TextView tvBtnExit;
    @BindView(R.id.tv_btn_sign)
    TextView tvBtnSign;

    private QianShouBean qianShouBean;
    //装箱单状态
    private int StateTag;
    //装箱单id
    private String encasementId;
    private String userId="";
    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_qianshou_info;
    }

    @Override
    protected void initView() {
        super.initView();
        userId= SharedPreferencesUtil.getInstance(ConfirmQianShouInfoActivity.this).getValueByKeyString(SharePreferenceKey.USERID,"");
        initData(getIntent());

    }

    /**
     * 根据装箱单号获取数据
     * @param intent
     */
    private void initData(Intent intent) {
        Bundle bundle = intent.getExtras();
        qianShouBean = (QianShouBean) bundle.getSerializable("qianShouBean");
        StateTag = bundle.getInt("StateTag", MainActivity.STATE_DEFAULT);
        //装箱单id
        encasementId = qianShouBean.getEncasementId();
        //售后服务商代码
        tvAfterSaleCode.setText(qianShouBean.getEncasementFacilitatorCode());
        //售后服务商名称
        tvAfterSaleName.setText(qianShouBean.getEncasementFacilitatorName());
        //周次日期
        tvDate.setText(qianShouBean.getPartsBatch());
        //件数
        tvPartNum.setText("共" + qianShouBean.getEncasementPartsNumber() + "件");
    }

    @OnClick({R.id.tv_btn_exit, R.id.tv_btn_sign})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_exit:
                finish();
                break;
            case R.id.tv_btn_sign:
                upload2Server();
                break;
        }
    }

    /**
     * 确认签收  将签收数据上传到后台
     */
    private void upload2Server() {
        TreeMap treeMap = new TreeMap();
        treeMap.put("encasementCode",qianShouBean.getEncasementCode());
        treeMap.put("userId", userId);
        MyHttpUtil.myHttpPost(AppContact.CONFIRMQIANSHOU, treeMap, new MyNetCallBack(this) {
            @Override
            public void MyOnSuccess(String result, String msg) {
                ActivityManger.goToMainActivity(ConfirmQianShouInfoActivity.this,StateTag);
                showToast(msg);
            }

            @Override
            public void MyOnFailure(String msg) {
                showToast(msg);
            }
        });
    }
}
