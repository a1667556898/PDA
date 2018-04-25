/*
 *
 *
 *         江城子 . 程序员之歌
 *
 *     十年生死两茫茫，写程序，到天亮。
 *         千行代码，Bug何处藏。
 *     纵使上线又怎样，朝令改，夕断肠。
 *
 *     领导每天新想法，天天改，日日忙。
 *         相顾无言，惟有泪千行。
 *     每晚灯火阑珊处，夜难寐，加班狂。
 *
 *
 */

package com.smcv.xyx.sh.claimpda.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.content.PermissionChecker;
import android.widget.TextView;

/**
 * author     zhangHeng
 * date :     2016/6/28 16:28.
 * describe:  检测工具类
 */
public class CheckUtil {
    /**
     * 检测是否有访问SDCard的`权限
     *
     * @param mContext
     * @return true 有 false 没有
     */
    public static boolean checkSDCardPower(Context mContext) {
        return PackageManager.PERMISSION_GRANTED == PermissionChecker.checkCallingOrSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     * 检测是否存在可以使用的SDCard
     *
     * @return true 存在 false 不存在
     */
    public static boolean isExistSDcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    public static boolean isNull(TextView textView) {
        return isNull(textView.getText().toString());
    }

    public static boolean isNull(String text) {
        if (text == null || "".equals(text) || "null".equals(text)) {
            return true;
        }
        return false;
    }

    public static boolean isNull(double d) {
        if (d > 0) {
            return true;
        }
        return false;
    }
}
