package com.websocket.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SendSMS {
    private static String sms = "http://202.85.221.42:9885";
    private static String smsMethod = "/c123/sendsms";
    private static String smsUid = "200152";
    private static String smsPwd = "tzy160";
    /**
     * @param convId  聊天室id
     * @param fromId  发消息人Id
     * @param toPeers 接收消息人Id
     * @param orderNo 订单编号
     * @param buyerSend 买家发送方式
     * @param sellerSend 卖家发送方式
     * @param code
     * @return
     */
//    public static boolean sendOrderSysMsg(String convId, String fromId, String toPeers, String buyerSend, String sellerSend, String orderNo, Integer code) {
//        String buyerMsg = "";
//        String sellerMsg = "";
//        String message = "";
//        switch (code) {
//            case 1:
//                message = "OrderFirstMessage";
//                buyerMsg = "您的订单：" + orderNo + " ，已创建成功，请尽快付款。";
//                sellerMsg = "您有新订单：" + orderNo + " ，买家拍下未付款，正在等待买家付款。";
//                break;
//            case 2:
//                message = "OrderPayCompleteMessage";
//                buyerMsg = "您的订单：" + orderNo + "，已付款完成，等待卖家收款后发货。";
//                sellerMsg = "您的订单：" + orderNo + " ，买家已付款，等待您收款后发货。请您尽快确认并发货";
//                break;
//            case 3:
//                message = "OrderPassCompleteMessage";
//                buyerMsg = "您的订单：" + orderNo + "，卖家已发货，交易完成。";
//                sellerMsg = "您的订单：" + orderNo + "，您已发货，交易完成。";
//                break;
//            case 4:
//                message = "OrderCancelMessage";
//                buyerMsg = "您的订单：" + orderNo + " 已取消，如有疑问，请联系客服。";
//                sellerMsg = "您的订单：" + orderNo + " 已被取消。";
//                break;
//            case 5:
//                message = "OrderAppealMessage";
//                buyerMsg = "您的订单：" + orderNo + " 正在申诉中，如有疑问，请联系客服。";
//                sellerMsg = "您的订单：" + orderNo + " 正在申诉中，如有疑问，请联系客服。";
//                break;
//            case 6:
//                message = "OrderAppealResultMessage";
//                buyerMsg = "您的订单：" + orderNo + " 订单申诉处理完成，如有疑问，请联系客服。";
//                sellerMsg = "您的订单：" + orderNo + " 订单申诉处理完成，如有疑问，请联系客服。";
//                break;
//        }
//        LeanCloudUtils.sendMsg(convId, fromId, toPeers, message);
//        if (StringUtil.checkMobileNumber(buyerSend)){
//            SendSMS.sendSMS(buyerSend, buyerMsg);
//        }else if (StringUtil.checkEmail(buyerSend)){
//            EmailUtil.sendEmail("【币酷】订单状态消息", buyerMsg, buyerSend);
//        }
//        if (StringUtil.checkMobileNumber(sellerSend)){
//            SendSMS.sendSMS(sellerSend, sellerMsg);
//        }else if (StringUtil.checkEmail(sellerSend)){
//            EmailUtil.sendEmail("【币酷】订单状态消息", sellerMsg, sellerSend);
//        }
//        return true;
//    }


    public static boolean sendSMS(String mobile, String msg) {
        try {
            Map<String, String> content = new HashMap<>();
            String md5PWD = MD5Util.md5(smsPwd);
            content.put("uid", smsUid);
            content.put("pwd", md5PWD);
            content.put("mobile", mobile);
            String encodeMsg = URLEncoder.encode("【币酷】" + msg, "gbk");
            content.put("content", encodeMsg);
            String result = SendSMS.do_get(sms + smsMethod, content);
            if (result.equals("100")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String do_get(String url, Map map) throws IOException {
        Iterator it = map.entrySet().iterator();
        String parm = "?";
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            parm += key + "=" + value + "&";
        }
        parm = parm.substring(0, parm.length() - 1);
        String str = url + parm;
        System.err.println(str);
        String body = "{}";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpget = new HttpGet(str);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            body = EntityUtils.toString(entity);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return body;
    }

    public static void main(String[] args) {
        boolean helloworld = sendSMS("18739910690", "您有一封邮件");
        System.out.println(helloworld);
    }
}
