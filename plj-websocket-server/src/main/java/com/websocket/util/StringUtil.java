package com.websocket.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.MessageFormatter;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 潘要东
 * @create 2018-04-02 13:33
 * @descriptions <p>字符串工具类</p>
 */
public final class StringUtil {

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 11:49
     * @descriptions <p>判断字符串为空(null,""," ","null")空格也为空</p>
     * @params [s]
     * @return boolean 为空则返回true，不否则返回false
     ****************************************************************************************************/
    public static boolean isBlank(String s){
        return StringUtils.isBlank(s) || "null".equals(s);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 11:49
     * @descriptions <p>判断字符串不为空(null,""," ","null")空格也为空</p>
     * @params [s]
     * @return boolean 不为空则返回true，否则返回false
     ****************************************************************************************************/
    public static boolean isNotBlank(String s){
        return StringUtils.isNotBlank(s) && !"null".equals(s);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 11:49
     * @descriptions <p>判断字符串为空(null,"","null")</p>
     * @params [s]
     * @return boolean 为空则返回true，不否则返回false
     ****************************************************************************************************/
    public static boolean isEmpty(String s){
        return StringUtils.isEmpty(s) || "null".equals(s);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 11:49
     * @descriptions <p>判断字符串不为空(null,"","null")</p>
     * @params [s]
     * @return boolean 不为空则返回true，否则返回false
     ****************************************************************************************************/
    public static boolean isNotEmpty(String s){
        return StringUtils.isNotEmpty(s) && !"null".equals(s);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 11:59
     * @descriptions <p>字符串内容替换</p>
     * @params [format, arg]
     * @return java.lang.String
     ****************************************************************************************************/
    public static String format(String s, Object arg) {
        return MessageFormatter.format(s, arg).getMessage();
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 11:59
     * @descriptions <p>字符串内容替换</p>
     * @params [format, arg1, arg2]
     * @return java.lang.String
     ****************************************************************************************************/
    public static String format(String s, Object arg1, Object arg2) {
        return MessageFormatter
                .format(s, arg1, arg2)
                .getMessage();
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 11:59
     * @descriptions <p>字符串内容替换</p>
     * @params [format, arg1, arg2, arg3]
     * @return java.lang.String
     ****************************************************************************************************/
    public static String format(String s, Object arg1, Object arg2, Object arg3) {
        return MessageFormatter
                .arrayFormat(s, new Object[]{arg1, arg2, arg3})
                .getMessage();
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 11:59
     * @descriptions <p>字符串内容替换</p>
     * @params [format, args]
     * @return java.lang.String
     ****************************************************************************************************/
    public static String format(String s, Object[] args) {
        return MessageFormatter
                .arrayFormat(s, args)
                .getMessage();
    }

    /**
     * StringUtil.toCamelCase()<BR>
     * <p>
     * Author : yinsq
     * </P>
     * <p>
     * Date : 2015年7月7日上午9:27:51
     * </P>
     * <p>
     * Desc : 转换字符串为驼峰命名法
     * </P>
     *
     * @param str
     * @return
     */
    public static String toCamelCase(String str, boolean isFirstToUpper) {
        if (str == null)
            return null;
        StringBuffer result = new StringBuffer();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (isFirstToUpper) {
                result.append(Character.toUpperCase(charArray[i]));
                isFirstToUpper = false;
            } else {
                if (i == 0) {
                    result.append(Character.toLowerCase(charArray[i]));
                    continue;
                }
                if (charArray[i] == '_') {
                    isFirstToUpper = true;
                    continue;
                }
                result.append(Character.toLowerCase(charArray[i]));
            }
        }
        return result.toString();
    }

    /**
     * StringUtil.toTableName()<BR>
     * <p>
     * Author : yinsq
     * </P>
     * <p>
     * Date : 2015年7月9日下午3:33:57
     * </P>
     * <p>
     * Desc : 把类名转换为带_的表名
     * </P>
     *
     * @param
     * @return
     */
    public static String toTableName(String str) {
        if (str == null)
            return null;
        StringBuffer result = new StringBuffer();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (i == 0) {
                result.append(charArray[i]);
                continue;
            }
            if (Character.isUpperCase(charArray[i])) {
                result.append("_");
            }
            result.append(charArray[i]);
        }
        return result.toString().toUpperCase();
    }

    public static String LETTERS_AND_NUMBERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static String LETTERS_AND_NUMBERS_IGNORE_CASE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private StringUtil() {
    }

    private static Random random = new Random();

    public static String generateRandomString(String set, int length) {
        StringBuilder builder = new StringBuilder(length);
        int setLength = set.length();
        for (int i = 0; i < length; i++) {
            builder.append(set.charAt(random.nextInt(setLength)));
        }
        return builder.toString();
    }

    private static final char[] TOKEN_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_-".toCharArray();

    public static String generateRandomToken(int len) {
        Random r = new Random();
        char[] s = new char[len];
        for (int i = 0; i < len; i += 4) {
            int v = r.nextInt(1 << 24);
            s[i] = TOKEN_CHARS[v >> 18];
            s[i + 1] = TOKEN_CHARS[(v >> 12) & 63];
            s[i + 2] = TOKEN_CHARS[(v >> 6) & 63];
            s[i + 3] = TOKEN_CHARS[v & 63];
        }
        return new String(s);
    }

    private static final char[] NUMBER_CHARS = "1234567890".toCharArray();

    public static String generateRandomNumber(int len) {
        Random r = new Random();
        char[] s = new char[len];
        for (int i = 0; i < len; i++) {
            s[i] = NUMBER_CHARS[r.nextInt(NUMBER_CHARS.length)];
        }
        return new String(s);
    }

    public static String getRandomColorCode() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;

        return r + g + b;
    }

    /**
     * 判断一个字串是否为空
     *
     * @param src
     * @return b
     */
    public static boolean isNull(String src) {
        return src == null || src.equals("");
    }

    /**
     * <p>
     * 1.JAVA的String.subString对汉字处理存在问题（把一个汉字视为一个字节)，因此在 包含汉字的字符串时存在隐患。
     * 2.由于JAVA的String的length是判断字符的个数，但后台数据库是根据指定编码（如GBK）保存数据的，
     * 所以JAVA的字符长度判断需根据字节判断。
     * <p>
     * </p>
     * <p>
     * 截取时考虑了第pLen、第pLen+1个字节为一个占两字节的字符的情况
     * </p>
     *
     * @param pContent 要截取的字符串
     * @param pLen     要截取的字节长度
     * @param charSet  字符串pContent的编码名称 UTF-8 OR GBK等
     * @return
     */
    public static String substringByteLen(String pContent, int pLen, Charset charSet) {

        if (!isNull(pContent) && charSet != null) {
            try {
                byte[] bytes = pContent.getBytes(charSet);

                if (bytes.length > pLen) {
                    int tempLen = new String(bytes, 0, pLen, charSet).length();
                    // 根据tempLen长度截取原字符串
                    pContent = pContent.substring(0, tempLen);

                    bytes = pContent.getBytes(charSet);
                    // 如果第totalLen、第totalLen+1个字节正好是一个汉字，
                    // String的substring方法会返回一个完整的汉字，导致长度为totalLen+1（超过totalLen），
                    // 所以再次对pContent的长度进行字节判断与处理
                    if (bytes.length > pLen) {
                        pContent = pContent.substring(0, tempLen - 1);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pContent;
    }

    /**
     * <p>
     * 1.JAVA的String.subString对汉字处理存在问题（把一个汉字视为一个字节)，因此在 包含汉字的字符串时存在隐患。
     * 2.由于JAVA的String的length是判断字符的个数，但后台数据库是根据指定编码（如GBK）保存数据的，
     * 所以JAVA的字符长度判断需根据字节判断。
     * <p>
     * </p>
     * <p>
     * 截取时考虑了第pLen、第pLen+1个字节为一个占两字节的字符的情况
     * </p>
     *
     * @param pContent 要截取的字符串
     * @param pLen     要截取的字节长度
     * @param charSet  字符串pContent的编码名称 UTF-8 OR GBK等
     * @return
     */
    public static String substringByteLen(String pContent, int pLen, String charSet) {
        Charset sourceStrCharset = Charset.forName(charSet);
        return substringByteLen(pContent, pLen, sourceStrCharset);
    }

    /**
     * @Title: substringByteLenDefaultUtf8 @Description: 默认处理的字符串格式为
     * UTF-8(不定长多个字节) @param pContent 要截取的字符串 @param pLen
     * 要截取的字节长度 @return String @throws
     */
    public static String substringByteLenDefaultUtf8(String pContent, int pLen) {
        Charset sourceStrCharset = Charset.forName("UTF-8");
        return substringByteLen(pContent, pLen, sourceStrCharset);
    }

    /**
     * @Title: substringByteLenDefaultGBK @Description: 默认处理的字符串格式为
     * GBK.(定长两个字节) @param pContent 要截取的字符串 @param pLen 要截取的字节长度 @return
     * String @throws
     */
    public static String substringByteLenDefaultGBK(String pContent, int pLen) {
        Charset sourceStrCharset = Charset.forName("GBK");
        return substringByteLen(pContent, pLen, sourceStrCharset);
    }

    /**
     * 将已知字符串截取长度为给定长度
     *
     * @param str    原字符串
     * @param length 要截取的长度
     * @return 截取后字符串
     */
    public static String substringCharByLength(String str, int length) {
        if (str != null && str.length() > length) {
            str = str.substring(0, length);
        }
        return str;
    }

    /**
     * @Title: getString2ByteLen @Description: 获得字符串的长度(java 自带的
     * str.length()会将汉字等非ascii 字符做为一个长度 ) @param content @param
     * charSet @return int @throws
     */
    public static int getString2ByteLen(String content, String charSet) {
        Charset sourceStrCharset = Charset.forName(charSet);
        return getString2ByteLen(content, sourceStrCharset);
    }

    public static int getString2ByteLen(String content, Charset charSet) {
        int length = -1;
        if (!isNull(content) && charSet != null) {
            try {
                byte[] bytes = content.getBytes(charSet);
                length = bytes.length;
            } catch (Exception e) {

            }
        }
        return length;
    }

    public static int getString2ByteLenDefaultGBK(String content) {
        return getString2ByteLen(content, "GBK");
    }

    public static int getString2ByteLenDefaultUTF8(String content) {
        return getString2ByteLen(content, "UTF-8");
    }

    /**
     * StringUtil.getSqlInStrByStrArray()<BR>
     * <P>Author :  zhangyq </P>
     * <P>Date : 2016年6月15日下午6:14:05</P>
     * <P>Desc : 数组字符串转换为SQL in 字符串拼接 </P>
     *
     * @param strArray 数组字符串
     * @return SQL in 字符串
     */
    public static String getSqlInStrByStrArray(String[] strArray) {
        StringBuffer temp = new StringBuffer();
        if (strArray != null && strArray.length > 0) {

            for (int i = 0; i < strArray.length; i++) {
                temp.append("'");
                temp.append(strArray[i]);
                temp.append("'");
                if (i != (strArray.length - 1)) {
                    temp.append(",");
                }
            }
        }
        return temp.toString();
    }

    /**
     * 去除字符串中的空格、回车、换行符、制表符等
     *
     * @param str
     * @return
     */
    public static String replaceSpecialStr(String str) {
        String repl = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            repl = m.replaceAll("");
        }
        return repl;
    }


}
