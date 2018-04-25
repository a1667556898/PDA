//package com.smcv.xyx.sh.claimpda.utils;
//
//import android.Manifest;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.content.res.Resources;
//import android.telephony.TelephonyManager;
//import android.util.DisplayMetrics;
//import android.util.Log;
//
//import com.smcv.xyx.sh.claimpda.base.ApplicationContext;
//
//import java.util.UUID;
//
///**
// * @author wangzizi
// * @date 2017/3/10 18:47
// * @Description: 从传媒移过来
// */
//public class DeviceUtil {
//    protected static String sDeviceId;
//    static int screen_width;
//    static int screen_height;
//    static int screen_density_dpi;
//    static int stateBar_height;
//    private static String packageName;
//
//    public static int getVerCode(Context context) {
//        int verCode = -1;
//        try {
//            verCode = context.getPackageManager().getPackageInfo("com.jinjiajinrong.zq", 0).versionCode;
//        } catch (PackageManager.NameNotFoundException e) {
//            Log.e("msg", e.getMessage());
//        }
//        return verCode;
//    }
//
//    public static String getVerName(Context context) {
//        String verName = "";
//        try {
//            verName = context.getPackageManager().getPackageInfo("com.jinjiajinrong.zq", 0).versionName;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return verName;
//    }
//
//    public static int getScreenWidth() {
//        if (screen_width == 0) {
//            initDeviceInfo();
//        }
//
//        return screen_width;
//    }
//
//    public static int getScreenHeight() {
//        if (screen_height == 0) {
//            initDeviceInfo();
//        }
//
//        return screen_height;
//    }
//
//    public static int getStateBarHeight() {
//        if (stateBar_height == 0) {
//            int resourceId = Resources.getSystem().getIdentifier(
//                    "status_bar_height", "dimen", "android");
//            if (resourceId > 0) {
//                stateBar_height = Resources.getSystem().getDimensionPixelSize(resourceId);
//            }
//        }
//        return stateBar_height;
//    }
//
//
//    public static int getScreenDpi() {
//        if (screen_density_dpi == 0) {
//            initDeviceInfo();
//        }
//
//        return screen_density_dpi;
//    }
//
//    public static String getDeviceId(String attributeName) {
//        if (sDeviceId != null) {
//            return sDeviceId.toString();
//        }
//        ApplicationContext context = ApplicationContext.getInstance();
//        synchronized (DeviceUtil.class) {
//            String deviceId = SharedPreferencesUtil.getString(context, attributeName);
//            SharedPreferencesUtil.getInstance(context).getValueByKeyString(attributeName,attributeName);
//            if (deviceId != null) {
//                sDeviceId = deviceId;
//                return sDeviceId;
//            }
//            //添加权限检查解决6.0问题
//            if (context.checkCallingPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED) {
//                //动态生成的deviceId
//                deviceId = String.valueOf(System.currentTimeMillis());
//            } else {
//                deviceId = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
//            }
//            if ("9774d56d682e549c".equals(deviceId)) {
//                deviceId = UUID.randomUUID().toString();
//            }
//            SharedPreferencesUtil.putString(context, attributeName, deviceId);
//            sDeviceId = deviceId;
//            return sDeviceId;
//        }
//    }
//
//    public static String getPackageName() {
//        ApplicationContext context = ApplicationContext.getInstance();
//        if (null == packageName) {
//
//            try {
//                packageName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//                packageName = "";
//            }
//        }
//        return packageName;
//    }
//
//    static void initDeviceInfo() {
//        DisplayMetrics displayMetrics = ApplicationContext.getInstance().getResources().getDisplayMetrics();
//        screen_width = displayMetrics.widthPixels;
//        screen_height = displayMetrics.heightPixels;
//        screen_density_dpi = displayMetrics.densityDpi;
//    }
//}