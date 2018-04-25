package com.smcv.xyx.sh.claimpda.ui;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.smcv.xyx.sh.claimpda.R;
import com.smcv.xyx.sh.claimpda.api.AppContact;
import com.smcv.xyx.sh.claimpda.base.BaseActivity;
import com.smcv.xyx.sh.claimpda.base.BaseApplication;
import com.smcv.xyx.sh.claimpda.base.SharePreferenceKey;
import com.smcv.xyx.sh.claimpda.customview.TitleBar;
import com.smcv.xyx.sh.claimpda.model.PermissionBean;
import com.smcv.xyx.sh.claimpda.utils.ActivityManger;
import com.smcv.xyx.sh.claimpda.utils.LogUtil;
import com.smcv.xyx.sh.claimpda.utils.SharedPreferencesUtil;
import com.smcv.xyx.sh.claimpda.utils.StringUtil;
import com.smcv.xyx.sh.claimpda.utils.httputil.MyHttpUtil;
import com.smcv.xyx.sh.claimpda.utils.httputil.MyNetCallBack;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_title_bar)
    TitleBar mainTitleBar;
    @BindView(R.id.tv_accept_carriage)
    TextView tvAcceptCarriage;
    @BindView(R.id.tv_warehouse_sign)
    TextView tvWarehouseSign;
    @BindView(R.id.tv_oldParts_enter)
    TextView tvOldPartsEnter;
    @BindView(R.id.tv_second_judgement)
    TextView tvSecondJudgement;
    @BindView(R.id.tv_oldParts_exit)
    TextView tvOldPartsExit;
    @BindView(R.id.ll_empty)
    LinearLayout ll_empty;
    @BindView(R.id.scrollview)
    ScrollView scrollview;
    @BindView(R.id.img_empty)
    ImageView img_empty;
    private PermissionBean permissionBean;

    /**
     * 装箱单的流程状态：
     * OLDIN_ENCASEMENT((short)0,"已装箱"),
     * GO_WULIU((short)1,"承运中"),
     * OLD_GET((short)2,"已签收"),
     * OLD_DELET((short)3,"已删除")
     * <p>
     * 零件流程状态：
     * WAIT_RELEASE((short)0,"待发布"),
     * OLD_RELEASE((short)1,"已发布"),
     * OLD_BACK((short)2,"已回运"),
     * OLD_ENCASEMENT((short)3,"已装箱"),
     * OLD_GO((short)4,"承运中"),
     * OLD_WRITE((short)5,"已签收"),
     * WAIT_JUDGE((short)6,"待判定"),
     * WAIT_GOSTORAGE((short)7,"待重新入库"),
     * OLD_STORAGE((short)8,"已入库"),
     * WAIT_DO((short)9,"待处理"),
     * OLD_OUTSTORAGE((short)10,"已出库"),
     * NONEED_RELEASE((short)11,"无需回运");
     */

    public static final int STATE_DAICHENGYUN = 0;//承运
    public static final int STATE_DAIQIANSHOU = 1;//签收
    public static final int STATE_YIJIARUQIANSHOUDAN = 4;//已加入签收单
    public static final int STATE_DAIRUKU = 4;//入库 承运中
    public static final int STATE_YIQIANSHOU = 5;//已签收
    public static final int STATE_DAICHONGXINRUKU = 7;//待重新入库
    public static final int STATE_DAIPANDING = 6;//二次判定
    public static final int STATE_DAICHUKU = 8;//出库
    public static final int STATE_KUWEI = 12;//库位 正常入库
    public static final int STATE_DEFAULT = -1;//默认-1
    //用户类型
    public static final int TYPE_WEIXIUZHAN = 10071007;//售后维修站
    public static final int TYPE_WULIU = 10071008;//售后莲花物流
    public static final int TYPE_CANGKU = 10071009;//售后仓库管理
    public static final int TYPE_ZHUJICHANG = 10071010;//主机厂（合并销售用户）所有的都显示

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        String token = SharedPreferencesUtil.getInstance(MainActivity.this).getValueByKeyString(SharePreferenceKey.TOKEN, "");
        LogUtil.i(Tag, "token:" + token);
        ll_empty.setVisibility(View.VISIBLE);
        scrollview.setVisibility(View.GONE);
        mainTitleBar.getTextRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManger.goToLoginActivity1(MainActivity.this, true);
                finish();
            }
        });
        //TODO 获取权限
        getPermission();
    }

    /**
     * 获取权限
     */
    private void getPermission() {
        MyHttpUtil.myHttpGet(AppContact.PERMISSION, new MyNetCallBack(MainActivity.this) {
            @Override
            public void MyOnSuccess(String result, String msg) {
                permissionBean = new Gson().fromJson(result, PermissionBean.class);
                if (!StringUtil.isEmpty(permissionBean.getUserId())) {
                    SharedPreferencesUtil.getInstance(MainActivity.this).putKVP(SharePreferenceKey.USERID, permissionBean.getUserId());
                }
                if (!StringUtil.isEmpty(permissionBean.getUserType() + "")) {
                    ll_empty.setVisibility(View.GONE);
                    scrollview.setVisibility(View.VISIBLE);
                    initPermission(permissionBean.getUserType());
                }

            }

            @Override
            public void MyOnFailure(String msg) {
                ll_empty.setVisibility(View.VISIBLE);
                scrollview.setVisibility(View.GONE);
            }
        });
    }

    //初始化权限
    private void initPermission(int userType) {
        switch (userType) {
            case TYPE_WEIXIUZHAN:
                tvSecondJudgement.setVisibility(View.VISIBLE);
                tvAcceptCarriage.setVisibility(View.GONE);
                tvWarehouseSign.setVisibility(View.GONE);
                tvOldPartsEnter.setVisibility(View.GONE);
                tvOldPartsExit.setVisibility(View.GONE);
                break;
            case TYPE_WULIU:
                tvSecondJudgement.setVisibility(View.GONE);
                tvAcceptCarriage.setVisibility(View.VISIBLE);
                tvWarehouseSign.setVisibility(View.GONE);
                tvOldPartsEnter.setVisibility(View.GONE);
                tvOldPartsExit.setVisibility(View.GONE);
                break;
            case TYPE_CANGKU:
                tvSecondJudgement.setVisibility(View.GONE);
                tvAcceptCarriage.setVisibility(View.GONE);
                tvWarehouseSign.setVisibility(View.VISIBLE);
                tvOldPartsEnter.setVisibility(View.VISIBLE);
                tvOldPartsExit.setVisibility(View.VISIBLE);
                break;
            case TYPE_ZHUJICHANG:
                tvSecondJudgement.setVisibility(View.VISIBLE);
                tvAcceptCarriage.setVisibility(View.VISIBLE);
                tvWarehouseSign.setVisibility(View.VISIBLE);
                tvOldPartsEnter.setVisibility(View.VISIBLE);
                tvOldPartsExit.setVisibility(View.VISIBLE);
                break;


        }
    }

    @OnClick({R.id.tv_accept_carriage, R.id.tv_warehouse_sign, R.id.tv_oldParts_enter, R.id.tv_second_judgement, R.id.tv_oldParts_exit, R.id.img_empty})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_accept_carriage://承运
                ActivityManger.goToScanCodeActivity(this, STATE_DAICHENGYUN);
                break;
            case R.id.tv_warehouse_sign://签收
                ActivityManger.goToScanCodeActivity(this, STATE_DAIQIANSHOU);
                break;
            case R.id.tv_oldParts_enter://入库
                ActivityManger.goToScanCodeActivity(this, STATE_DAIRUKU);
                break;
            case R.id.tv_second_judgement://二次判定
                ActivityManger.goToScanCodeActivity(this, STATE_DAIPANDING);
                break;
            case R.id.tv_oldParts_exit://出库
                ActivityManger.goToScanCodeActivity(this, STATE_DAICHUKU);
                break;
            case R.id.img_empty:
                getPermission();
                break;
        }
    }

    // 再按一次退出程序
    private long exitTime = 0;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showToast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                BaseApplication.getInstance().destroy();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
