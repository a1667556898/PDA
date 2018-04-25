
/*
 * *
 *  *                      江城子 . 程序员之歌
 *  *
 *  *                  十年生死两茫茫，写程序，到天亮。
 *  *                      千行代码，Bug何处藏。
 *  *                  纵使上线又怎样，朝令改，夕断肠。
 *  *
 *  *                  领导每天新想法，天天改，日日忙。
 *  *                      相顾无言，惟有泪千行。
 *  *                  每晚灯火阑珊处，夜难寐，加班狂。
 * /
 */

package com.smcv.xyx.sh.claimpda.utils.httputil;

public class RestResponseCode {
    public static final String SUCCESS = "0";//请求成功
    public static final String ERROR_NULL = "E_000";
    public static final String ERROR_NO_CONNECT = "E_001";
    public static final String UNAUTHORIZED = "1011";//用户未登录
    public static final String TOKEN_FAIL = "4002";//token失效 异地登录
}
