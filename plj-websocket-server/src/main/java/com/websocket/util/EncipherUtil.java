package com.websocket.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author 潘要东
 * @create 2018-04-23 15:20
 * @descriptions <p>加密解密工具类</p>
 */
public class EncipherUtil {

    private final static String MD5 = "MD5";
    private final static char[] HEX_DIGITS_C = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final static char[] HEX_DIGITS_S = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/23 15:21
     * @descriptions <p>MD5加密:参数为NULL时, 返回NULL。加密失败时, 返回NULL。</p>
     * @params [source] 未加密字符串
     * @return java.lang.String 已加密字符串
     ****************************************************************************************************/
    public static String encryptMD5(String source) {
        if (source == null) {
            return null;
        }
        try {
            //MD5摘要算法
            MessageDigest md = MessageDigest.getInstance(MD5);

            //更新摘要
            byte[] src = source.getBytes(StandardCharsets.UTF_8);
            md.update(src);

            //密文
            byte[] target = md.digest();

            //密文转换成十六进制
            final int SIZE = target.length;
            int n = 0;
            char[] result = new char[SIZE * 2];

            for (byte b : target) {
                result[n++] = HEX_DIGITS_S[b >>> 4 & 0xf];
                result[n++] = HEX_DIGITS_S[b & 0xf];
            }

            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
