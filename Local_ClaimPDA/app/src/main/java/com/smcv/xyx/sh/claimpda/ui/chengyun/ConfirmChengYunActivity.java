package com.smcv.xyx.sh.claimpda.ui.chengyun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smcv.xyx.sh.claimpda.R;
import com.smcv.xyx.sh.claimpda.api.AppContact;
import com.smcv.xyx.sh.claimpda.base.BaseActivity;
import com.smcv.xyx.sh.claimpda.base.SharePreferenceKey;
import com.smcv.xyx.sh.claimpda.model.ChengYunBean;
import com.smcv.xyx.sh.claimpda.ui.MainActivity;
import com.smcv.xyx.sh.claimpda.utils.ActivityManger;
import com.smcv.xyx.sh.claimpda.utils.LogUtil;
import com.smcv.xyx.sh.claimpda.utils.SharedPreferencesUtil;
import com.smcv.xyx.sh.claimpda.utils.StringUtil;
import com.smcv.xyx.sh.claimpda.utils.httputil.MyHttpUtil;
import com.smcv.xyx.sh.claimpda.utils.httputil.MyNetCallBack;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wangxin on 15/03/2018.
 */

public class ConfirmChengYunActivity extends BaseActivity {

    @BindView(R.id.tv_afterSale_code)
    TextView tvAfterSaleCode;
    @BindView(R.id.tv_afterSale_name)
    TextView tvAfterSaleName;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_part_num)
    TextView tvPartNum;
    @BindView(R.id.tv_confirm_carriage)
    TextView tvConfirmCarriage;
    @BindView(R.id.tv_continue_scan)
    TextView tvContinueScan;

    private int StateTag = -1;
    private ChengYunBean chengYunBean;
    private String encasementIds = "";//装箱单id集合

    private String userId="";

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_chengyun;
    }

    @Override
    protected void initView() {
        super.initView();
        userId= SharedPreferencesUtil.getInstance(ConfirmChengYunActivity.this).getValueByKeyString(SharePreferenceKey.USERID,"");
        initDatas(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initDatas(intent);
    }

    @OnClick({R.id.tv_confirm_carriage, R.id.tv_continue_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm_carriage:
                //确认承运 上传数据到服务器 成功提示 失败提示
                if (!StringUtil.isEmpty(encasementIds)) {
                    uploadDataToServer(encasementIds.substring(0, encasementIds.length() - 1));
                } else {
                    showToast("数据格式不对");
                }
                break;
            case R.id.tv_continue_scan:
                ActivityManger.goToScanCodeActivity(ConfirmChengYunActivity.this, StateTag);
                break;
        }
    }

    /**
     * 初始化数据
     *
     * @param intent
     */
    public void initDatas(Intent intent) {
        String encasementId = "";//装箱单id集合
        Bundle bundle = intent.getExtras();
        chengYunBean = (ChengYunBean) bundle.getSerializable("chengYunBean");
        StateTag = bundle.getInt("StateTag", MainActivity.STATE_DEFAULT);

        if (null != chengYunBean){
            //售后服务商代码
            tvAfterSaleCode.setText(chengYunBean.getEncasementFacilitatorCode());
            //售后服务商名称
            tvAfterSaleName.setText(chengYunBean.getEncasementFacilitatorName());
            //周次日期
            tvDate.setText(chengYunBean.getPartsBatch());
            //件数
            tvPartNum.setText("其中" + chengYunBean.getEncasementPartsNumber() + "件");
        }


        encasementId = chengYunBean.getEncasementCode();
        if (!StringUtil.isEmpty(encasementId)) {
            encasementIds = encasementIds + encasementId + ",";
        }
        LogUtil.i(Tag, encasementIds);
    }

    /**
     * 确认承运
     */
    private void uploadDataToServer(String encasementIds) {
        TreeMap treeMap = new TreeMap();
        treeMap.put("encasementCodes", encasementIds);
        treeMap.put("userId", userId);
        MyHttpUtil.myHttpPost(AppContact.CONFIRMCHENGYUN, treeMap, new MyNetCallBack(this) {
            @Override
            public void MyOnSuccess(String result, String msg) {
                showToast(msg);
                ActivityManger.goToMainActivity(ConfirmChengYunActivity.this, -1);
            }

            @Override
            public void MyOnFailure(String msg) {
                showToast(msg);
            }
        });
    }
}
