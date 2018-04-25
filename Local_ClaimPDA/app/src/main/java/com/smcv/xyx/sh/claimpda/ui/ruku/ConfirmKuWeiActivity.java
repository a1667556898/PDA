package com.smcv.xyx.sh.claimpda.ui.ruku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smcv.xyx.sh.claimpda.R;
import com.smcv.xyx.sh.claimpda.api.AppContact;
import com.smcv.xyx.sh.claimpda.base.BaseActivity;
import com.smcv.xyx.sh.claimpda.base.SharePreferenceKey;
import com.smcv.xyx.sh.claimpda.customview.TitleBar;
import com.smcv.xyx.sh.claimpda.model.RuKuBean;
import com.smcv.xyx.sh.claimpda.utils.ActivityManger;
import com.smcv.xyx.sh.claimpda.utils.SharedPreferencesUtil;
import com.smcv.xyx.sh.claimpda.utils.httputil.MyHttpUtil;
import com.smcv.xyx.sh.claimpda.utils.httputil.MyNetCallBack;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wangxin on 19/03/2018.
 */

public class ConfirmKuWeiActivity extends BaseActivity {
    @BindView(R.id.main_title_bar)
    TitleBar mainTitleBar;
    @BindView(R.id.tv_house_name)
    TextView tvHouseName;
    @BindView(R.id.tv_house_num)
    TextView tvHouseNum;
    @BindView(R.id.tv_afterSale_code)
    TextView tvAfterSaleCode;
    @BindView(R.id.tv_afterSale_name)
    TextView tvAfterSaleName;
    @BindView(R.id.tv_vin_num)
    TextView tvVinNum;
    @BindView(R.id.tv_engine_no)
    TextView tvEngineNo;
    @BindView(R.id.tv_return_weekly)
    TextView tvReturnWeekly;
    @BindView(R.id.tv_line_no)
    TextView tvLineNo;
    @BindView(R.id.tv_claim_num)
    TextView tvClaimNum;
    @BindView(R.id.tv_claim_type)
    TextView tvClaimType;
    @BindView(R.id.tv_parts_code)
    TextView tvPartsCode;
    @BindView(R.id.tv_parts_name)
    TextView tvPartsName;
    @BindView(R.id.tv_parts_num)
    TextView tvPartsNum;
    @BindView(R.id.tv_btn_exit)
    TextView tvBtnExit;
    @BindView(R.id.tv_btn_confirm)
    TextView tvBtnConfirm;
    @BindView(R.id.rl_house_name)
    RelativeLayout rlHouseName;
    @BindView(R.id.rl_house_code)
    RelativeLayout rlHouseCode;

    private RuKuBean ruKuBean;
    private int stateTag;
    private int ruKuType;

    private String reason;
    private String reasonDesc;
    private String kuWeiId="";
    private String userId="";
    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_kuwei;
    }

    @Override
    protected void initView() {
        super.initView();
        userId= SharedPreferencesUtil.getInstance(ConfirmKuWeiActivity.this).getValueByKeyString(SharePreferenceKey.USERID,"");

        initData(getIntent());
    }


    /**
     * 初始化数据
     * @param intent
     */
    private void initData(Intent intent) {
        Bundle bundle = intent.getExtras();
        //配件状态
        stateTag = intent.getExtras().getInt("StateTag");
        //入库原因
        reason = bundle.getString("reason");
        //原因描述
        reasonDesc = bundle.getString("reasonDesc");
        //库位号
        ruKuType=bundle.getInt("ruKuType");
        ruKuBean= (RuKuBean) bundle.getSerializable("mruKuBean");

        if (ruKuType==0){ //正常入库
            rlHouseName.setVisibility(View.VISIBLE);
            rlHouseCode.setVisibility(View.VISIBLE);

        }else if (ruKuType==1){ //二次判定
            rlHouseName.setVisibility(View.GONE);
            rlHouseCode.setVisibility(View.GONE);
        }

        if (null!=ruKuBean){
            kuWeiId = SharedPreferencesUtil.getInstance(ConfirmKuWeiActivity.this).getValueByKeyString(SharePreferenceKey.KUIWEISEAT,"");
            String kuWeiAddress=SharedPreferencesUtil.getInstance(ConfirmKuWeiActivity.this).getValueByKeyString(SharePreferenceKey.KUIWEIADDRESS,"");
            tvHouseName.setText(kuWeiAddress);
            tvHouseNum.setText(kuWeiId);
            tvAfterSaleCode.setText(ruKuBean.getParts().getPartsAscCode());
            tvAfterSaleName.setText(ruKuBean.getParts().getPartsAscName());
            tvVinNum.setText(ruKuBean.getParts().getPartsVin());
            tvEngineNo.setText(ruKuBean.getParts().getPartsEngineNo());
            tvReturnWeekly.setText(ruKuBean.getParts().getPartsBatch());
            tvLineNo.setText(ruKuBean.getParts().getPartsLineNo());
            tvClaimNum.setText(ruKuBean.getParts().getPartsRoNo());
            tvClaimType.setText(ruKuBean.getParts().getPartsClaimType());
            tvPartsCode.setText(ruKuBean.getParts().getPartsReturnPartCode());
            tvPartsName.setText(ruKuBean.getParts().getPartsReturnPartName());
            tvPartsNum.setText(ruKuBean.getParts().getPartsQuantity());
        }
    }

    @OnClick({R.id.tv_btn_exit, R.id.tv_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_exit:
                finish();
                break;
            case R.id.tv_btn_confirm:
                uploadData2Server();
                break;
        }
    }

    /**
     * 入库 上传数据到后台
     */
    private void uploadData2Server() {
        TreeMap treeMap = new TreeMap();
        if (ruKuBean != null && ruKuType==0){
                //partsId
            treeMap.put("partsId", ruKuBean.getParts().getPartsId());
                //状态标志 0：正常入库 1：二次判定
            treeMap.put("putStorageState", ruKuType);
                //仓库名称
            treeMap.put("putStorageAddressId",ruKuBean.getStorage().get(0).getStorageDictionarysId());
                //库位号
            treeMap.put("putStorageSeat", kuWeiId);
            treeMap.put("userId", userId);
            }else if (ruKuBean != null && ruKuType==1){
                // 获取partsId
            treeMap.put("partsId", ruKuBean.getParts().getPartsId());
                //状态标志 0：正常入库 1：二次判定
            treeMap.put("putStorageState", ruKuType);
                //待定原因
            treeMap.put("partsSecondJudgeReason",reason);
                //待定原因 其他 描述
            treeMap.put("partsOtherJudgeReason",reasonDesc);
            treeMap.put("userId", userId);
        }
        MyHttpUtil.myHttpPost(AppContact.CONFIRMRUKU, treeMap, new MyNetCallBack(this) {
            @Override
            public void MyOnSuccess(String result, String msg) {
                ActivityManger.goToMainActivity(ConfirmKuWeiActivity.this,stateTag);
                showToast(msg);
            }
            @Override
            public void MyOnFailure(String msg) {
                showToast(msg);
            }
        });
    }

}
