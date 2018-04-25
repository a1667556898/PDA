package com.smcv.xyx.sh.claimpda.ui.ercipanding;

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
import com.smcv.xyx.sh.claimpda.api.AppContact;
import com.smcv.xyx.sh.claimpda.base.BaseActivity;
import com.smcv.xyx.sh.claimpda.base.SharePreferenceKey;
import com.smcv.xyx.sh.claimpda.customview.TitleBar;
import com.smcv.xyx.sh.claimpda.model.PanDingBean;
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
 * Created by wangxin on 14/03/2018.
 */

public class ConfirmInfoErCiActivity extends BaseActivity {
    @BindView(R.id.main_title_bar)
    TitleBar mainTitleBar;
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
    @BindView(R.id.daiding_reason)
    TextView daidingReason;
    @BindView(R.id.tv_daiding_desc)
    TextView tvDaidingDesc;
    @BindView(R.id.ll_result_spinner)
    Spinner llResultSpinner;
    @BindView(R.id.rl_fact_num)
    RelativeLayout rl_fact_num;
    @BindView(R.id.et_fact_num)
    EditText etFactNum;
    @BindView(R.id.et_feedback_result)
    EditText etFeedbackResult;
    @BindView(R.id.tv_btn_exit)
    TextView tvBtnExit;
    @BindView(R.id.tv_btn_confirm)
    TextView tvBtnConfirm;
    private PanDingBean panDingBean;

    private int StateTag;
    //实际输入数量
    private String factNum = "0";
    //反馈结果
    private String feedbackNum = "";
    //待定原因数据设置
    private String[] datas;
    //待定原因value
    private String value;
    //原因描述 只有选择“其他”才有
    private String resultDesc;
    private PanDingBean.PartsBean partsBean;
    private String userId="";
    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_info_erci;
    }

    @Override
    protected void initView() {
        super.initView();
        userId= SharedPreferencesUtil.getInstance(ConfirmInfoErCiActivity.this).getValueByKeyString(SharePreferenceKey.USERID,"");

        initData(getIntent());
    }

    /**
     * 初始化数据
     * @param intent
     */
    private void initData(Intent intent) {
        Bundle bundle = intent.getExtras();
        panDingBean = (PanDingBean) bundle.getSerializable("panDingBean");
        StateTag = bundle.getInt("StateTag", MainActivity.STATE_DEFAULT);
        partsBean = panDingBean.getParts();
        //售后服务商代码
        tvAfterSaleCode.setText(partsBean.getPartsAscCode());
        //售后服务商名称
        tvAfterSaleName.setText(partsBean.getPartsAscName());
        //VIN
        tvVinNum.setText(partsBean.getPartsVin());
        //发动机号
        tvEngineNo.setText(partsBean.getPartsEngineNo());
        //回运周次
        tvReturnWeekly.setText(partsBean.getPartsBatch());
        //行号
        tvLineNo.setText(partsBean.getPartsLineNo());
        //索赔单号
        tvClaimNum.setText(partsBean.getPartsRoNo());
        //索赔类型
        tvClaimType.setText(partsBean.getPartsClaimType());
        //索赔配件代码
        tvPartsCode.setText(partsBean.getPartsReturnPartCode());
        //索赔配件名称
        tvPartsName.setText(partsBean.getPartsReturnPartName());
        //索赔零件数量
        tvPartsNum.setText(partsBean.getPartsQuantity());
        //待定原因
        tvDaidingDesc.setText(partsBean.getPartsOtherJudgeReason());
        //实际入库数量 默认为索赔配件数量
        etFactNum.setText(partsBean.getPartsQuantity());
        //实际入库数量 默认值
        factNum = partsBean.getPartsQuantity();
        //spinner数据设置
        // 建立数据源
        datas=new String[panDingBean.getProcessState().size()];
        LogUtil.i("222","panDingBean:"+panDingBean.getProcessState().size());
        for (int i=0;i<panDingBean.getProcessState().size();i++){
            datas[i]=panDingBean.getProcessState().get(i).getLabel();
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.custom_spinner_item,datas);
        llResultSpinner.setAdapter(adapter);
        llResultSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {//待重新入库
                    rl_fact_num.setVisibility(View.VISIBLE);
                    if (!StringUtil.isEmpty(etFactNum.getText()+"")){
                        factNum=etFactNum.getText()+"";
                    }
                }else {
                    rl_fact_num.setVisibility(View.GONE);
                    factNum = "0";
                }
                value = panDingBean.getProcessState().get(position).getValue();
                resultDesc = llResultSpinner.getSelectedItem().toString();
                LogUtil.i(Tag,"你点击了：" + value +"" + "值为： " + resultDesc);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //实际数量
        etFactNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                factNum = s.toString();
            }
        });
        //反馈数量
        etFeedbackResult.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                feedbackNum = s.toString();
            }
        });
    }

    @OnClick({R.id.tv_btn_exit, R.id.tv_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_exit:
                ActivityManger.goToMainActivity(ConfirmInfoErCiActivity.this,1);
                break;
            case R.id.tv_btn_confirm: //确认按钮
                TreeMap treeMap = new TreeMap();
                treeMap.put("partsId",panDingBean.getParts().getPartsId());
                treeMap.put("userId", userId);
                //判定结果 传index
                treeMap.put("partsProcessState",value);
                if (StringUtil.isEmpty(factNum) ){
                    showToast("请输入实际入库数量！");
                    return;
                }else if (Integer.parseInt(factNum)>Integer.parseInt(partsBean.getPartsQuantity())){
                    showToast("实际入库数量不能大于索赔零件数量！");
                    return;
                }else {
                    treeMap.put("putStoragePartsNumber",factNum);
                }
                //反馈结果
                if (StringUtil.isEmpty(feedbackNum)){
                    showToast("反馈结果不能为空");
                    return;
                }else {
                    treeMap.put("partsReturnResult",feedbackNum);
                }
                MyHttpUtil.myHttpPost(AppContact.CONFIRMPANDING, treeMap, new MyNetCallBack(this) {
                    @Override
                    public void MyOnSuccess(String result, String msg) {
                        ActivityManger.goToMainActivity(ConfirmInfoErCiActivity.this,-MainActivity.STATE_DEFAULT);
                        showToast(msg);
                    }

                    @Override
                    public void MyOnFailure(String msg) {
                        showToast(msg);
                    }
                });

                break;
        }
    }
}
