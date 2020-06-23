package com.websocket.util;

import java.util.UUID;

/**
 * @author 潘要东
 * @create 2018-04-24 10:46
 * @descriptions <p>UUID工具类</p>
 */
public class UUIDUtil {

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 11:44
     * @descriptions <p>生成一个UUID</p>
     * @params []
     * @return java.util.UUID UUID对象
     ****************************************************************************************************/
    public static UUID uuid() {
        return UUID.randomUUID();
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 11:44
     * @descriptions <p>生成一个UUID字符串</p>
     * @params []
     * @return java.lang.String UUID字符串
     ****************************************************************************************************/
    public static String uuidChars() {
//        String s = UUID.randomUUID().toString();
//        //去掉“-”符号
//        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
//        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
        return uuid().toString().replace("-", "");
    }
}
