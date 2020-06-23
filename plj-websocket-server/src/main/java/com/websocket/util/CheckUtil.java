package com.websocket.util;

import java.net.URL;
import java.util.regex.Pattern;

/**
 * @author 潘要东
 * @create 2018-04-24 14:34
 * @descriptions <p></p>
 */
public class CheckUtil {

    private final static Pattern IntegerPattern;
    private final static Pattern DatePattern;
    private final static Pattern DatetimePattern;
    private final static Pattern EmailPattern;
    private final static Pattern PhonePattern;

    static {
        IntegerPattern = Pattern.compile("^[-\\+]?[\\d]*$");
        DatePattern = Pattern.compile("");
        DatetimePattern = Pattern.compile("");
        EmailPattern = Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        PhonePattern = Pattern.compile("^(((13[0-9])|(14[5,7])|(15([0-3]|[5-9]))|(16[6])|(17[0-9])|(18[0-9])|(19[8,9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 14:44
     * @descriptions <p>校验手机号</p>
     * @params [arg]
     * @return boolean
     ****************************************************************************************************/
    public static boolean isMobile(String arg) {
        if (StringUtil.isBlank(arg)) return false;
        if (arg.length() != 11) return false;
        char[] mob = new char[11];
        for (int i = 0; i < 11; i++) {
            mob[i] = arg.charAt(i);
            if (mob[i] < '0' || mob[i] > '9') return false;
        }
        if (mob[0] != '1') return false;
        if (mob[1] == '0' ||
                mob[1] == '1' ||
                mob[1] == '2') {
            return false;
        }
        if (mob[1] == '4') {
            if (!(mob[2] == '5' ||
                    mob[2] == '7')) return false;
        }
        if (mob[1] == '5') {
            if (mob[2] == '4') return false;
        }else if (mob[1] == '7') {
            if (!(mob[2] == '0' ||
                    mob[2] == '6' ||
                    mob[2] == '7' ||
                    mob[2] == '8')) {
                return false;
            }
        }
        if (mob[1] == '6') {
            if (mob[2] != '6') return false;
        }
        if (mob[1] == '9') {
            if (!(mob[2] == '8' ||
                    mob[2] == '9')) {
                return false;
            }
        }
        return true;
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 14:44
     * @descriptions <p>正则表达式校验字符串</p>
     * @params [regex,arg]
     * @return boolean
     ****************************************************************************************************/
    public static boolean isMatch(String regex, String arg){
        if (StringUtil.isBlank(arg)) return false;
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(arg).matches();
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 14:44
     * @descriptions <p>校验手机号</p>
     * @params [arg]
     * @return boolean
     ****************************************************************************************************/
    public static boolean isPhone(String arg) {
        if(StringUtil.isBlank(arg)) return false;
        return PhonePattern.matcher(arg).matches();
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 14:45
     * @descriptions <p>校验邮箱</p>
     * @params [arg]
     * @return boolean
     ****************************************************************************************************/
    public static boolean isEmail(String arg) {
        if(StringUtil.isBlank(arg)) return false;
        return EmailPattern.matcher(arg).matches();
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 14:45
     * @descriptions <p>校验数字</p>
     * @params [arg]
     * @return boolean
     ****************************************************************************************************/
    public static boolean isNumber(String arg) {
        if (StringUtil.isBlank(arg)) {
            return false;
        }
        for (int i = 0; i < arg.length(); i++) {
            char ch = arg.charAt(i);
            if (ch < '0' || ch > '9') {
                return false;
            }
        }
        return true;
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 14:45
     * @descriptions <p>校验整数</p>
     * @params [arg]
     * @return boolean
     ****************************************************************************************************/
    public static boolean isInteger(String arg) {
        if (StringUtil.isBlank(arg)) return false;
        return IntegerPattern.matcher(arg).matches();
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 14:45
     * @descriptions <p>校验是否［日期］格式（yyyy-MM-dd）</p>
     * @params [arg]
     * @return boolean
     ****************************************************************************************************/
    public static boolean isDate(String arg) {
        if (StringUtil.isBlank(arg)) return false;
        return DatePattern.matcher(arg).matches();
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 14:45
     * @descriptions <p>校验是否［日期＋时间］格式（yyyy-MM-dd hh:mm:ss）</p>
     * @params [arg]
     * @return boolean
     ****************************************************************************************************/
    public static boolean isDatetime(String arg) {
        if (StringUtil.isBlank(arg)) return false;
        return DatetimePattern.matcher(arg).matches();
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 14:45
     * @descriptions <p>校验URL</p>
     * @params [arg]
     * @return boolean
     ****************************************************************************************************/
    public static boolean isURL(String arg) {
        boolean OK = true;
        try {
            new URL(arg);
        } catch (Exception e) {
            OK = false;
        }
        return OK;
    }
}
