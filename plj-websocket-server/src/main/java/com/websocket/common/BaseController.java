package com.websocket.common;

import com.websocket.util.DataMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 对controller层公共方法的封装
 * @author PLJ
 * @date 2018年3月20日11:36:33
 */
@CrossOrigin(origins = "*", maxAge = 3600)
public class BaseController {

  /*  @ModelAttribute
    public void common(HttpServletRequest request, HttpServletResponse response) {
        //执行公共的东西
        System.out.println("init方法");
    }*/

    /**
     * 获取request
     *
     * @return
     */
    protected HttpServletRequest getRequest() {
        HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return request;
    }

    /**
     * 获取参数
     *
     * @return
     */
    public DataMap getDataMap() {
        return new DataMap(this.getRequest());
    }
    /**
     * 获取参数
     *
     * @return
     */
//    public String  getToken() {
//
//        return getRequest().getHeader("XX-Token");
//    }



    /**
     * 获取response
     *
     * @return
     */
    protected HttpServletResponse getResponse() {
        HttpServletResponse response=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Type", "application/json;charset=UTF-8");
//                response.setContentType("application/json; charset=utf-8");
        return response;
    }

    /**
     * 获取session对象
     *
     * @return
     */
    protected HttpSession getSession() {
        HttpSession session = this.getRequest().getSession();
        return session;
    }

    /**
     * 获取当前网络ip
     *
     * @param
     * @return
     */
    public String getIpAddr() {
        HttpServletRequest request = getRequest();
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 判断浏览器来源
     */
    public String checkAgentIsMobile() {
        String[] agent = {"Android", "iPhone", "iPod", "iPad", "Windows Phone", "MQQBrowser"};
        String ua = getRequest().getHeader("User-Agent");
        String from = "未知来源";
        if (ua.contains("Windows NT") || (ua.contains("Windows NT") && ua.contains("compatible; MSIE 9.0;"))) {
            from = "pc";
        }
        // 排除 苹果桌面系统
        if (!ua.contains("Windows NT") && !ua.contains("Macintosh")) {
            if (ua.indexOf("MicroMessenger") > 0) {// 是微信浏览器
                from = "微信";
                return from;
            }
            for (String item : agent) {
                if (ua.contains(item)) {
                    from = "手机";
                    break;
                }
            }
        }
        return from;
    }
}

