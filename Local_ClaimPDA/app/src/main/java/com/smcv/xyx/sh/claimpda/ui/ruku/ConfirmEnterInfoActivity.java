package com.smcv.xyx.sh.claimpda.ui.ruku;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.smcv.xyx.sh.claimpda.R;
import com.smcv.xyx.sh.claimpda.base.BaseActivity;
import com.smcv.xyx.sh.claimpda.base.SharePreferenceKey;
import com.smcv.xyx.sh.claimpda.customview.CustomerDialog;
import com.smcv.xyx.sh.claimpda.model.RuKuBean;
import com.smcv.xyx.sh.claimpda.ui.MainActivity;
import com.smcv.xyx.sh.claimpda.utils.ActivityManger;
import com.smcv.xyx.sh.claimpda.utils.LogUtil;
import com.smcv.xyx.sh.claimpda.utils.SharedPreferencesUtil;
import com.smcv.xyx.sh.claimpda.utils.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wangxin on 15/03/2018.
 */

public class ConfirmEnterInfoActivity extends BaseActivity {

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
    @BindView(R.id.ll_enter_spinner)
    Spinner llEnterSpinner;
    @BindView(R.id.rl_reason)
    RelativeLayout rlReason;
    @BindView(R.id.ll_reason_spinner)
    Spinner llReaspmSpinner;
    @BindView(R.id.et_reason_desc)
    EditText etReasonDesc;
    @BindView(R.id.tv_btn_exit)
    TextView tvBtnExit;
    @BindView(R.id.tv_btn_confirm)
    TextView tvBtnConfirm;

    private int StateTag;
    private RuKuBean ruKuBean;
    private String inputStr = "";
    private int selectedItemPosition;
    private String label = "其他";
    private String value;
    private String reasonDesc;
    private String[] datas;

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_enter_info;
    }

    @Override
    protected void initView() {
        super.initView();
        initDatas(getIntent());
        etReasonDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                inputStr = s.toString();
            }
        });

    }

    /**
     * 初始化数据
     *
     * @param intent
     */
    private void initDatas(Intent intent) {
        Bundle bundle = intent.getExtras();
        ruKuBean = (RuKuBean) bundle.getSerializable("ruKuBean");
        StateTag = bundle.getInt("StateTag", MainActivity.STATE_DEFAULT);

        if (null != ruKuBean) {
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

        //入库类型选择 正常入库or二次判定
        llEnterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemPosition = position;
                if (position == 0) { //正常入库 待定原因和原因描述不可用
                    llReaspmSpinner.setEnabled(false);
                    etReasonDesc.setEnabled(false);
                } else if (position == 1) { //二次判定 待定原因和原因描述可用
                    llReaspmSpinner.setEnabled(true);
                    etReasonDesc.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spinner数据设置
        // 建立数据源
        datas = new String[ruKuBean.getCommonList().getResult().size()];
        for (int i = 0; i < ruKuBean.getCommonList().getResult().size(); i++) {
            datas[i] = ruKuBean.getCommonList().getResult().get(i).getLabel();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_spinner_item, datas);
        llReaspmSpinner.setAdapter(adapter);
        //待定原因选择
        //如果选择其他，要加入原因描述，以及获取状态的key和value
        llReaspmSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (ruKuBean != null) {
                    //脚标
                    value = ruKuBean.getCommonList().getResult().get(position).getValue();
                    //描述
                    label = ruKuBean.getCommonList().getResult().get(position).getLabel();
                    if (!label.equals("其他")) {
                        etReasonDesc.setText(label);
                        etReasonDesc.setFocusableInTouchMode(false);
                        etReasonDesc.setFocusable(false);
                        etReasonDesc.requestFocus();
                    } else if (label.equals("其他")) {
                        etReasonDesc.getText().clear();
                        etReasonDesc.setFocusableInTouchMode(true);
                        etReasonDesc.setFocusable(true);
                        etReasonDesc.requestFocus();
                        etReasonDesc.setHint("请输入原因描述");
                    }
                    LogUtil.i(Tag, value + ":" + label);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.tv_btn_exit, R.id.tv_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_exit:
                finish();
                break;
            case R.id.tv_btn_confirm: //确认按钮
                if (selectedItemPosition == 0) { //正常入库 根据是否有库位 如果有提示是否用新库位 否则进入扫描库位界面
                    String KUIWEISEAT = SharedPreferencesUtil.getInstance(ConfirmEnterInfoActivity.this).getValueByKeyString(SharePreferenceKey.KUIWEISEAT, "");
                    if (StringUtil.isEmpty(KUIWEISEAT)) {
                        ActivityManger.goToScanCodeActivityFromRuku(ConfirmEnterInfoActivity.this, MainActivity.STATE_KUWEI, selectedItemPosition, ruKuBean);
                    } else {
                        CustomerDialog customerDialog = new CustomerDialog();
                        customerDialog.showSelectDialog(ConfirmEnterInfoActivity.this, "库位号：" + KUIWEISEAT, "否", "是");
                        customerDialog.setMyCancleOnClick(new CustomerDialog.MyCancleOnClick() {
                            @Override
                            public void myCancleOnClick() {
                                ActivityManger.goToScanCodeActivityFromRuku(ConfirmEnterInfoActivity.this, MainActivity.STATE_KUWEI, selectedItemPosition, ruKuBean);
                            }
                        });
                        customerDialog.setMyOkOnClick(new CustomerDialog.MyOkOnClick() {
                            @Override
                            public void myokonclick() {
                                Intent intent = new Intent(ConfirmEnterInfoActivity.this, ConfirmKuWeiActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("mruKuBean", ruKuBean);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                    }


                } else if (selectedItemPosition == 1) {//二次判定
                    if (null != label && label.equals("其他")) { //其他 原因描述
                        reasonDesc = etReasonDesc.getText().toString();
                    } else { //非其他
                        reasonDesc = etReasonDesc.getText().toString();
                    }
                    if (StringUtil.isEmpty(reasonDesc)) {
                        showToast("请输入原因描述");
                        return;
                    }
                    LogUtil.i(Tag, "原因描述： " + reasonDesc);
                    //跳转最终入库界面
                    ActivityManger.goToConfirmKuWeiActivity(ConfirmEnterInfoActivity.this, StateTag, label, reasonDesc, "", selectedItemPosition, ruKuBean);
                }
                break;
        }

    }

}
