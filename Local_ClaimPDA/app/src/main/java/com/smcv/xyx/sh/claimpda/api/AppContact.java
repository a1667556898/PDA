package com.smcv.xyx.sh.claimpda.api;

/**
 * Created by wangxin on 12/03/2018.
 */

public class AppContact {

    //线上环境
    public static final String BASEURL = "http://10.64.58.247/rpart";
    //大通环境
//    public static final String BASEURL = "http://10.64.58.248:1001";
    //冯永杰个人环境
//    public static final String BASEURL = "http://192.168.2.4:1001";
    //吴志文
//    public static final String BASEURL = "http://10.64.58.194:1001";
    //杨超帅
//    public static final String BASEURL = "http://192.168.31.200:1001";
    //查冠友10.64.58.149
//    public static final String BASEURL = "http://192.168.253.3:1001";
    //测试环境
//    public static final String BASEURL = "http://10.108.2.49:2001";
    //登录接口
    public static final String LOGIN = BASEURL + "/usersLanding";

    //登录
    public static final String LOADURL = "http://10.64.58.247/authLogin/loginByMobile?appCode=rpart&appKey=1e275634e3f9d11b755c5d956d4d149c54f5d802";
    //退出登录
    public static final String EXITURL = "http://10.64.58.247/authLogin/logoutByMobile?appKey=1e275634e3f9d11b755c5d956d4d149c54f5d802";
    //协议
    public static final String XIEYI = "http://smcvpda/";

    //检查版本接口
    public static final String CHECKVERSION =BASEURL + "";
    //承运
    public static final String CHENGYUN = BASEURL + "/portal/signature/pda/read";
    //确认承运
    public static final String CONFIRMCHENGYUN =BASEURL +  "/portal/signature/pda/accept";
    //签收
    public static final String QIANSHOU = BASEURL + "/portal/signature/pda/read";
    //确认签收
    public static final String CONFIRMQIANSHOU = BASEURL + "/portal/signature/pda/updateStateByCode";
    //入库
    public static final String RUKU =BASEURL +  "/putStorage//pda/findAppStorageInfo";
    //确认入库
    public static final String CONFIRMRUKU = BASEURL + "/putStorage//pda/partsAppRegister";
    //库位和仓库信息
    public static final String KUWEI = BASEURL + "/putStorage/pda/checkAddressSeat";
    //判定
    public static final String PANDING =BASEURL +  "/secondJudge/pda/clickPdaSecondJudge";
    //确认判定
    public static final String CONFIRMPANDING =BASEURL +  "/secondJudge/pda/secondJudgePdaConfirm";
    //出库
    public static final String CHUKU =BASEURL +  "/portal/outstorage/pda/read";
    //创建出库单
    public static final String CONFIRMCHUKU = BASEURL + "/portal/outstorage/pda/add";

    //获取权限
    public static final String PERMISSION = BASEURL + "/queryUserRole";
}
