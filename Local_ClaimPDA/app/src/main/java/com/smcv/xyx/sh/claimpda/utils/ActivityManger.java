package com.smcv.xyx.sh.claimpda.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.smcv.xyx.sh.claimpda.model.ChengYunBean;
import com.smcv.xyx.sh.claimpda.model.ChuKuBean;
import com.smcv.xyx.sh.claimpda.model.ChuKuNewBean;
import com.smcv.xyx.sh.claimpda.model.PanDingBean;
import com.smcv.xyx.sh.claimpda.model.QianShouBean;
import com.smcv.xyx.sh.claimpda.model.RuKuBean;
import com.smcv.xyx.sh.claimpda.ui.MainActivity;
import com.smcv.xyx.sh.claimpda.ui.ScanCodeActivity;
import com.smcv.xyx.sh.claimpda.ui.chengyun.ConfirmChengYunActivity;
import com.smcv.xyx.sh.claimpda.ui.chuku.ChuKuConfirmInfoActivity;
import com.smcv.xyx.sh.claimpda.ui.chuku.ChuKuConfirmSecondActivity;
import com.smcv.xyx.sh.claimpda.ui.ercipanding.ConfirmInfoErCiActivity;
import com.smcv.xyx.sh.claimpda.ui.housesign.ConfirmQianShouInfoActivity;
import com.smcv.xyx.sh.claimpda.ui.login.LoginActivity1;
import com.smcv.xyx.sh.claimpda.ui.ruku.ConfirmEnterInfoActivity;
import com.smcv.xyx.sh.claimpda.ui.ruku.ConfirmKuWeiActivity;

import java.util.ArrayList;

/**
 * Created by SummerChen on 2018/3/19.
 */

public class ActivityManger {

    /**
     * 跳转MainActivity
     * @param context
     * @param StateTag 状态标签
     */
    public static void goToMainActivity(Context context, int StateTag) {
        Intent intent = new Intent(context, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("StateTag", StateTag);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    /**
     * 跳转扫描界面
     * @param context
     * @param StateTag 状态标签
     */
    public static void goToScanCodeActivity(Context context, int StateTag) {
        Intent intent = new Intent(context, ScanCodeActivity.class);
        intent.putExtra("StateTag", StateTag);
        context.startActivity(intent);
    }
    /**
     * 跳转扫描界面
     * @param context
     * @param StateTag 状态标签
     */
    public static void goToScanCodeActivityFromRuku(Context context, int StateTag,int ruKuType,RuKuBean mruKuBean) {
        Intent intent = new Intent(context, ScanCodeActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("mruKuBean",mruKuBean);
        bundle.putInt("ruKuType",ruKuType);
        intent.putExtra("StateTag", StateTag);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    /**
     * 跳转承运装箱单信息
     * @param context
     * @param StateTag 状态标签
     * @param chengYunBean
     */
    public static void goToConfirmChengYunActivity(Context context, int StateTag,ChengYunBean chengYunBean) {
        Intent intent = new Intent(context, ConfirmChengYunActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("chengYunBean",chengYunBean);
        bundle.putInt("StateTag", StateTag);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 跳转确认签收信息
     * @param context
     * @param StateTag 状态标签
     * @param qianShouBean  配件id
     */
    public static void goToConfirmQianShouInfoActivity(Context context, int StateTag,QianShouBean qianShouBean) {
        Intent intent = new Intent(context, ConfirmQianShouInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("qianShouBean",qianShouBean);
        bundle.putInt("StateTag", StateTag);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 跳转确认入库信息
     * @param context
     * @param StateTag 状态标签
     * @param ruKuBean  零件id
     */
    public static void goToConfirmEnterInfoActivity(Context context, int StateTag,RuKuBean ruKuBean) {
        Intent intent = new Intent(context, ConfirmEnterInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ruKuBean",ruKuBean);
        bundle.putInt("StateTag", StateTag);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 跳转确认库位信息
     * @param context
     * @param StateTag 状态标签
     * @param reasonDesc 待定原因描述
     */
    public static void goToConfirmKuWeiActivity(Context context, int StateTag,String reason,String reasonDesc,String kuWeiId,int ruKuType,RuKuBean mruKuBean) {
        Intent intent = new Intent(context, ConfirmKuWeiActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("StateTag",StateTag);
        bundle.putString("reason",reason);
        bundle.putString("reasonDesc",reasonDesc);
        bundle.putString("kuWeiId",kuWeiId);
        bundle.putInt("ruKuType",ruKuType);
        bundle.putSerializable("mruKuBean",mruKuBean);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 跳转确认二次判定信息
     * @param context
     * @param StateTag 状态标签
     * @param panDingBean  配件id
     */
    public static void goToConfirmErCiActivity(Context context, int StateTag,PanDingBean panDingBean) {
        Intent intent = new Intent(context, ConfirmInfoErCiActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("panDingBean", panDingBean);
        bundle.putInt("StateTag", StateTag);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 跳转确认出库信息
     * @param context
     * @param StateTag 状态标签
     * @param chuKuBean  配件id
     */
    public static void goToChuKuConfirmInfoActivity(Context context, int StateTag,String partsId,ChuKuBean chuKuBean) {
        Intent intent = new Intent(context, ChuKuConfirmInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("chuKuBean", chuKuBean);
        bundle.putInt("StateTag", StateTag);
        bundle.putString("partsId",partsId);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 跳转出库完成信息(创建出库单)
     * @param context
     * @param StateTag
     * @param
     */
    public static void goToChuKuConfirmSecondActivity(Context context, int StateTag, ArrayList<ChuKuNewBean> chuKuNewBeens) {
        Intent intent = new Intent(context, ChuKuConfirmSecondActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("chuKuNewBeens",chuKuNewBeens);
        bundle.putInt("StateTag",StateTag);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 跳转登录界面
     * @param context
     * @param
     */
    public static void goToLoginActivity1(Context context, boolean isFromExit) {
        Intent intent = new Intent(context, LoginActivity1.class);
        intent.putExtra("isFromExit",isFromExit);
        context.startActivity(intent);
    }
}
