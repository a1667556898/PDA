package com.smcv.xyx.sh.claimpda.ui.chuku;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.smcv.xyx.sh.claimpda.R;
import com.smcv.xyx.sh.claimpda.base.BaseActivity;
import com.smcv.xyx.sh.claimpda.model.ChuKuBean;
import com.smcv.xyx.sh.claimpda.model.ChuKuNewBean;
import com.smcv.xyx.sh.claimpda.ui.MainActivity;
import com.smcv.xyx.sh.claimpda.utils.ActivityManger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wangxin on 15/03/2018.
 */

public class ChuKuConfirmInfoActivity extends BaseActivity {
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
    @BindView(R.id.tv_note_left)
    TextView tvNoteLeft;
    @BindView(R.id.et_note)
    EditText etNote;
    @BindView(R.id.tv_complete_scan)
    TextView tvCompleteScan;
    @BindView(R.id.tv_continue_scan)
    TextView tvContinueScan;

    private int StateTag;
    private ChuKuBean chuKuBean;
    private String note = ""; //备注
    private String peiJianCode = ""; //配件代码
    private String peiJianName = ""; //配件名称
    private boolean isAdd = false;//是否已经添加过
    private String partsId="";//配件id
    private ChuKuNewBean chuKuNewBean = new ChuKuNewBean();
    private ArrayList<ChuKuNewBean> chuKuNewBeens = new ArrayList<>();//连续扫描后的数据集合

    @Override
    public int getLayoutId() {
        return R.layout.activity_chuku_confirm;
    }

    @Override
    protected void initView() {
        super.initView();
        setText(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setText(intent);
    }

    @Override
    protected void listener() {
        super.listener();
        etNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                note = s.toString();
            }
        });
    }


    private void setText(Intent intent) {
        Bundle bundle = intent.getExtras();
        chuKuBean = (ChuKuBean) bundle.getSerializable("chuKuBean");
        if (null != chuKuBean) {
            StateTag = bundle.getInt("StateTag", MainActivity.STATE_DEFAULT);
            partsId = chuKuBean.getPartsId();
            tvAfterSaleCode.setText(chuKuBean.getPartsAscCode() + "");
            tvAfterSaleName.setText(chuKuBean.getPartsAscName());
            tvVinNum.setText(chuKuBean.getPartsVin());
            tvEngineNo.setText(chuKuBean.getPartsEngineNo());
            tvReturnWeekly.setText(chuKuBean.getPartsBatch());
            tvLineNo.setText(chuKuBean.getPartsLineNo());
            tvClaimNum.setText(chuKuBean.getPartsRoNo());
            tvClaimType.setText(chuKuBean.getPartsClaimType());
            tvPartsNum.setText(chuKuBean.getPartsQuantity());

            peiJianCode = chuKuBean.getPartsReturnPartCode();
            peiJianName = chuKuBean.getPartsReturnPartName();
            tvPartsCode.setText(peiJianCode);
            tvPartsName.setText(peiJianName);
            note = "";//置空
            etNote.setText("");
        }
    }

    @OnClick({R.id.tv_complete_scan, R.id.tv_continue_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //完成扫描  需要判断实际出库数据和描述是否为空
            case R.id.tv_complete_scan:
                setChuKuNewBean(true);
                break;
            //继续扫描 需要判断实际出库数据和描述是否为空
            case R.id.tv_continue_scan:
                setChuKuNewBean(false);
                break;
        }
    }
    /**
     * 添加数据到集合
     * paramers isComplete:是否跳转到完成
     */
    private void setChuKuNewBean(boolean isComplete) {
            isAdd = false;//制为false
            for (int i = 0; i < chuKuNewBeens.size(); i++) {
                if (partsId.equals(chuKuNewBeens.get(i).getPartsId())) {
                    isAdd = true;
                }
            }

            if (!isAdd) {
                chuKuNewBean = new ChuKuNewBean();
                chuKuNewBean.setPartsRemark(note);
                chuKuNewBean.setPartsReturnPartCode(peiJianCode);
                chuKuNewBean.setPartsReturnPartName(peiJianName);
                chuKuNewBean.setPartsId(partsId);
                chuKuNewBeens.add(chuKuNewBean);
            }

            if (isComplete) {
                ActivityManger.goToChuKuConfirmSecondActivity(ChuKuConfirmInfoActivity.this, StateTag, chuKuNewBeens);
            } else {
                ActivityManger.goToScanCodeActivity(ChuKuConfirmInfoActivity.this, StateTag);
            }
    }
}


