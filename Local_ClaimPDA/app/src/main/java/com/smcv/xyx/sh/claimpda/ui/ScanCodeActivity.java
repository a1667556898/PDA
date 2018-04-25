package com.smcv.xyx.sh.claimpda.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.device.ScanManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.smcv.xyx.sh.claimpda.R;
import com.smcv.xyx.sh.claimpda.api.AppContact;
import com.smcv.xyx.sh.claimpda.base.BaseActivity;
import com.smcv.xyx.sh.claimpda.base.SharePreferenceKey;
import com.smcv.xyx.sh.claimpda.model.ChengYunBean;
import com.smcv.xyx.sh.claimpda.model.ChuKuBean;
import com.smcv.xyx.sh.claimpda.model.KuWeiBean;
import com.smcv.xyx.sh.claimpda.model.PanDingBean;
import com.smcv.xyx.sh.claimpda.model.QianShouBean;
import com.smcv.xyx.sh.claimpda.model.RuKuBean;
import com.smcv.xyx.sh.claimpda.ui.ruku.ConfirmKuWeiActivity;
import com.smcv.xyx.sh.claimpda.utils.ActivityManger;
import com.smcv.xyx.sh.claimpda.utils.LogUtil;
import com.smcv.xyx.sh.claimpda.utils.SharedPreferencesUtil;
import com.smcv.xyx.sh.claimpda.utils.StringUtil;
import com.smcv.xyx.sh.claimpda.utils.ToastUtil;
import com.smcv.xyx.sh.claimpda.utils.httputil.MyHttpUtil;
import com.smcv.xyx.sh.claimpda.utils.httputil.MyNetCallBack;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by wangxin on 13/03/2018.
 */

public class ScanCodeActivity extends BaseActivity {

    @BindView(R.id.tv_scan_desc)
    TextView tvScanDesc;
    @BindView(R.id.et_hint)
    EditText etHint;
    @BindView(R.id.click_btn_cancel)
    TextView clickBtnCancel;
    @BindView(R.id.click_btn_confirm)
    TextView clickBtnConfirm;
    @BindView(R.id.text_mid)
    TextView textMid;
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.text_left)
    TextView textLeft;

    private int StateTag;//状态标签

    private static final String SACN_ACTION = "urovo.rcv.message";//扫描结束action

    private Vibrator mVibrator;//声音
    private ScanManager mScanManger;//扫描管理类
    private SoundPool soundPool = null;//声音池

    private int soundid;
    private String barcodeStr = "";//扫描后返回的数据
    private boolean isScaning = false;
        private boolean isPDA = true;//是不是pda设备
    private String etInputStr = "";//输入框输入的数据

    private ChengYunBean chengYunBean;
    private ChuKuBean chuKuBean;
    private QianShouBean qianShouBean;
    private PanDingBean panDingBean;
    private RuKuBean ruKuBean;
    private KuWeiBean kuWeiBean;

    //正常入库传数据
    private int ruKuType;
    private RuKuBean mruKuBean;
    private String userId="";


    private BroadcastReceiver mScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            isScaning = false;
            barcodeStr = "";//置空
            soundPool.play(soundid, 1, 1, 0, 0, 1);
            mVibrator.vibrate(100);

            byte[] barcode = intent.getByteArrayExtra("barocode");
            int barocodelen = intent.getIntExtra("length", 0);
            byte temp = intent.getByteExtra("barcodeType", (byte) 0);
            barcodeStr = new String(barcode, 0, barocodelen);
            LogUtil.i(Tag, "barcodeStr:" + barcodeStr);
            Log.i(Tag, "barcodeStr:" + barcodeStr);
            if (StringUtil.isEmpty(barcodeStr)) {
                showToast("扫描结果不能为空");
                return;
            }
            //分情况处理 扫描
            switch (StateTag) {
                case MainActivity.STATE_DAICHENGYUN:
                    getChengYunDataFromService(barcodeStr);
                    break;
                case MainActivity.STATE_DAIQIANSHOU:
                    getQianShouDataFromServer(barcodeStr);
                    break;
                case MainActivity.STATE_DAIRUKU:
                    getRuKuDataFromServer(barcodeStr);
                    break;
                case MainActivity.STATE_DAIPANDING:
                    getPanDingDataFromServer(barcodeStr);
                    break;
                case MainActivity.STATE_DAICHUKU:
                    getChuKuDataFromServer(barcodeStr);
                    break;
                case MainActivity.STATE_KUWEI:
                    getKuWeiDataFromServer(barcodeStr);
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan_code;
    }

    @Override
    protected void initView() {
        super.initView();

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        StateTag = getIntent().getIntExtra("StateTag", MainActivity.STATE_DEFAULT);
        if (StateTag == MainActivity.STATE_DEFAULT) {
            showToast("状态码不正确");
            finish();
        }
        userId=SharedPreferencesUtil.getInstance(ScanCodeActivity.this).getValueByKeyString(SharePreferenceKey.USERID,"");
        ruKuType = getIntent().getExtras().getInt("ruKuType");
        mruKuBean = (RuKuBean) getIntent().getExtras().getSerializable("mruKuBean");

        //分情况处理
        switch (StateTag) {
            case MainActivity.STATE_DAICHENGYUN:
                textMid.setText("扫描装箱单二维码");
                tvScanDesc.setText("扫描装箱单二维码");
                etHint.setHint("请输入装箱单号");
                clickBtnConfirm.setText("确认承运");
                break;
            case MainActivity.STATE_DAIQIANSHOU:
                textMid.setText("扫描装箱单二维码");
                tvScanDesc.setText("扫描装箱单二维码");
                etHint.setHint("请输入装箱单号");
                clickBtnConfirm.setText("完成");
                break;
            case MainActivity.STATE_DAIRUKU:
                textMid.setText("扫描配件二维码");
                tvScanDesc.setText("扫描配件二维码");
                etHint.setHint("请输入配件编号");
                clickBtnConfirm.setText("完成");
                break;
            case MainActivity.STATE_DAIPANDING:
                textMid.setText("扫描配件二维码");
                tvScanDesc.setText("扫描配件二维码");
                etHint.setHint("请输入配件编号");
                clickBtnConfirm.setText("完成");
                break;
            case MainActivity.STATE_DAICHUKU:
                textMid.setText("扫描配件二维码");
                tvScanDesc.setText("扫描配件二维码");
                etHint.setHint("请输入配件编号");
                clickBtnConfirm.setText("完成");
                break;
            case MainActivity.STATE_KUWEI://备用
                textMid.setText("扫描库位二维码");
                tvScanDesc.setText("扫描库位二维码");
                etHint.setHint("请输入库位号");
                clickBtnConfirm.setText("完成");
                break;
        }

        etHint.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                etInputStr = s.toString();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPDA) {
            mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            initScan();
            IntentFilter filter = new IntentFilter();
            filter.addAction(SACN_ACTION);
            registerReceiver(mScanReceiver, filter);

            barcodeStr = "";//置空
            mScanManger.stopDecode();
            isScaning = true;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mScanManger.startDecode();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isPDA) {
            if (null != mScanManger) {
                mScanManger.stopDecode();
                isScaning = false;
            }
            unregisterReceiver(mScanReceiver);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化扫描
     */
    private void initScan() {
        mScanManger = new ScanManager();
        mScanManger.openScanner();
        mScanManger.switchOutputMode(0);
        soundPool = new SoundPool(1, AudioManager.STREAM_NOTIFICATION, 100);
        soundid = soundPool.load("/etc/Scan_new.ogg", 1);
    }

    //手动输入
    @OnClick({R.id.img_left, R.id.click_btn_cancel, R.id.click_btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                finish();
                break;
            case R.id.click_btn_cancel:
                finish();
                break;
            case R.id.click_btn_confirm:
                if (StringUtil.isEmpty(etInputStr)) {
                    showToast("输入内容不能为空");
                    return;
                }
                //分情况处理
                switch (StateTag) {
                    //承运
                    case MainActivity.STATE_DAICHENGYUN:
                        getChengYunDataFromService(etInputStr);
                        break;
                    //签收
                    case MainActivity.STATE_DAIQIANSHOU:
                        getQianShouDataFromServer(etInputStr);
                        break;
                    //入库
                    case MainActivity.STATE_DAIRUKU:
                        getRuKuDataFromServer(etInputStr);
                        break;
                    //二次判定
                    case MainActivity.STATE_DAIPANDING:
                        getPanDingDataFromServer(etInputStr);
                        break;
                    //出库
                    case MainActivity.STATE_DAICHUKU:
                        getChuKuDataFromServer(etInputStr);
                        break;
                    //库位
                    case MainActivity.STATE_KUWEI:
                        getKuWeiDataFromServer(etInputStr);
                        break;
                }
        }
    }

    /**
     * 根据装箱单id获取签收数据
     *
     * @param zhuangXiangId
     */
    private void getQianShouDataFromServer(String zhuangXiangId) {
        TreeMap treeMap = new TreeMap();
        treeMap.put("encasementCode", zhuangXiangId);
        treeMap.put("userId", userId);
        MyHttpUtil.myHttpPost(AppContact.QIANSHOU, treeMap, new MyNetCallBack(this) {
            @Override
            public void MyOnSuccess(String result, String msg) {
                qianShouBean = new Gson().fromJson(result, QianShouBean.class);
                if (null != qianShouBean && !StringUtil.isEmpty(qianShouBean.getEncasementState())
                        && Integer.parseInt(qianShouBean.getEncasementState()) == MainActivity.STATE_DAIQIANSHOU) {
                    ActivityManger.goToConfirmQianShouInfoActivity(ScanCodeActivity.this, StateTag, qianShouBean);
                } else {
                    ToastUtil.getShortToast(ScanCodeActivity.this, "状态码不正确", 2000);
                }
            }

            @Override
            public void MyOnFailure(String msg) {
                showToast(msg);
            }
        });
    }

    /**
     * 根据配件id获取出库数据
     *
     * @param partsId
     */
    private void getChuKuDataFromServer(final String partsId) {
        TreeMap treeMap = new TreeMap();
        treeMap.put("partsId", partsId);
        treeMap.put("userId", userId);
        MyHttpUtil.myHttpPost(AppContact.CHUKU, treeMap, new MyNetCallBack(this) {
            @Override
            public void MyOnSuccess(String result, String msg) {
                chuKuBean = new Gson().fromJson(result, ChuKuBean.class);
                if (null != chuKuBean && !StringUtil.isEmpty(chuKuBean.getPartsProcessState())
                        && Integer.parseInt(chuKuBean.getPartsProcessState()) == MainActivity.STATE_DAICHUKU) {

                    ActivityManger.goToChuKuConfirmInfoActivity(ScanCodeActivity.this, StateTag, partsId, chuKuBean);
                } else {
                    ToastUtil.getShortToast(ScanCodeActivity.this, "状态码不正确", 2000);
                }
            }

            @Override
            public void MyOnFailure(String msg) {
                showToast(msg);
            }
        });
    }

    /**
     * 根据配件Id获取判定数据
     *
     * @param partsId
     */
    private void getPanDingDataFromServer(String partsId) {
        TreeMap treeMap = new TreeMap();
        treeMap.put("partsId", partsId);
        treeMap.put("userId", userId);
        MyHttpUtil.myHttpPost(AppContact.PANDING, treeMap, new MyNetCallBack(this) {
            @Override
            public void MyOnSuccess(String result, String msg) {
                panDingBean = new Gson().fromJson(result, PanDingBean.class);
                String encasementState = String.valueOf(panDingBean.getParts().getPartsProcessState());
                if (!StringUtil.isEmpty(encasementState) && Integer.parseInt(encasementState) == MainActivity.STATE_DAIPANDING) {
                    ActivityManger.goToConfirmErCiActivity(ScanCodeActivity.this, StateTag, panDingBean);
                } else {
                    ToastUtil.getShortToast(ScanCodeActivity.this, "状态码不正确", 2000);
                }
            }

            @Override
            public void MyOnFailure(String msg) {
                showToast(msg);
            }
        });
    }

    /**
     * 根据零件ID获取入库信息
     *
     * @param partsId 零件ID
     */
    private void getRuKuDataFromServer(String partsId) {
        TreeMap treeMap = new TreeMap();
        treeMap.put("partsId", partsId);
        treeMap.put("userId", userId);
        MyHttpUtil.myHttpPost(AppContact.RUKU, treeMap, new MyNetCallBack(this) {
            @Override
            public void MyOnSuccess(String result, String msg) {
                ruKuBean = new Gson().fromJson(result, RuKuBean.class);
                if (null != ruKuBean && !StringUtil.isEmpty(ruKuBean.getParts().getPartsProcessState()) && (Integer.parseInt(ruKuBean.getParts().getPartsProcessState()) == MainActivity.STATE_DAIRUKU || Integer.parseInt(ruKuBean.getParts().getPartsProcessState()) == MainActivity.STATE_YIQIANSHOU || Integer.parseInt(ruKuBean.getParts().getPartsProcessState()) == MainActivity.STATE_DAICHONGXINRUKU)) {
                    ActivityManger.goToConfirmEnterInfoActivity(ScanCodeActivity.this, StateTag, ruKuBean);
                } else {
                    ToastUtil.getShortToast(ScanCodeActivity.this, "状态码不正确", 2000);
                }
            }

            @Override
            public void MyOnFailure(String msg) {
                showToast(msg);
            }
        });
    }

    /**
     * 跳转到入库页面
     *
     * @param kuWeiId 库位号
     */
    private void getKuWeiDataFromServer(String kuWeiId) {
        TreeMap treeMap = new TreeMap();
        treeMap.put("partsId", mruKuBean.getParts().getPartsId());
        treeMap.put("storageSeat", kuWeiId);
        treeMap.put("userId", userId);
        LogUtil.i(Tag, mruKuBean.getParts().getPartsId());
        MyHttpUtil.myHttpPost(AppContact.KUWEI, treeMap, new MyNetCallBack(this) {
            @Override
            public void MyOnSuccess(String result, String msg) {
                kuWeiBean = new Gson().fromJson(result, KuWeiBean.class);
                showToast(msg);
                if (null != kuWeiBean) {
                    //把库位号保存起来以备下次使用
                    String kuweiAddress = "";
                    String kuweiSeat = "";
                    kuweiAddress = kuWeiBean.getStorageAddress();
                    kuweiSeat = kuWeiBean.getStorageSeat();
                    SharedPreferencesUtil.getInstance(ScanCodeActivity.this).putKVP(SharePreferenceKey.KUIWEIADDRESS, kuweiAddress);
                    SharedPreferencesUtil.getInstance(ScanCodeActivity.this).putKVP(SharePreferenceKey.KUIWEISEAT, kuweiSeat);
                    Intent intent = new Intent(ScanCodeActivity.this, ConfirmKuWeiActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("mruKuBean", mruKuBean);
                    intent.putExtras(bundle);
                    startActivity(intent);

                } else {
                    ToastUtil.getShortToast(ScanCodeActivity.this, "状态码不正确", 2000);
                }
            }

            @Override
            public void MyOnFailure(String msg) {
                showToast(msg);
            }
        });
    }

    /**
     * 根据装箱单号获取承运数据
     *
     * @param NumZhuangxiang 装箱单号
     */
    public void getChengYunDataFromService(String NumZhuangxiang) {
        TreeMap treeMap = new TreeMap();
        treeMap.put("encasementCode", NumZhuangxiang);
        treeMap.put("userId", userId);
        MyHttpUtil.myHttpPost(AppContact.CHENGYUN, treeMap, new MyNetCallBack(this) {
            @Override
            public void MyOnSuccess(String result, String msg) {
                chengYunBean = new Gson().fromJson(result, ChengYunBean.class);
                if (null != chengYunBean && !StringUtil.isEmpty(chengYunBean.getEncasementState()) && (Integer.parseInt(chengYunBean.getEncasementState()) == MainActivity.STATE_DAICHENGYUN||Integer.parseInt(chengYunBean.getEncasementState()) == MainActivity.STATE_YIJIARUQIANSHOUDAN)) {
                    ActivityManger.goToConfirmChengYunActivity(ScanCodeActivity.this, StateTag, chengYunBean);
                } else {
                    ToastUtil.getShortToast(ScanCodeActivity.this, "状态码不正确", 2000);
                }
            }
            @Override
            public void MyOnFailure(String msg) {
                showToast(msg);
            }
        });
    }
}
