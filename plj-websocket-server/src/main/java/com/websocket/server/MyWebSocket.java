package com.websocket.server;

import com.alibaba.fastjson.JSONObject;
import com.websocket.service.UserService;
import com.websocket.util.DataMap;
import com.websocket.util.GZipUtils;
import com.websocket.util.StringUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

//当前用户ID
@ServerEndpoint("/webSocket/{userId}")
@Component
public class MyWebSocket {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MyWebSocket.class);

    //1:客户端每建立一个链接就会创建一个对象,所以静态注入https://blog.csdn.net/lotts/article/details/77944672
    private static UserService userService;

    @Autowired
    public void setBibiService(UserService userService) {
        MyWebSocket.userService = userService;
    }

//    2:构造注入也可以,必须有空参构造
//    public MyWebSocket(){
//    }
//    @Autowired
//    public MyWebSocket(BibiService bibiService){
//        this.bibiService = bibiService;
//    }

    /* 如果要解决这种线程安全的问题,不能通过线程安全的集合来保存Session解决。
     * 而应该保存整个类，并通过CopyOnWriteArraySet容器来操作
     * */
    public static CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<Session>();

    //存放所有在线的客户端，单一客户端通信（线程安全Set）
    private static ConcurrentHashMap<String, Session> clients = new ConcurrentHashMap<String, Session>();


    /**
     * 存放所有在线的客户端
     */
//    private static Map<String, Session> clients = new ConcurrentHashMap<>();


    //连接
    @OnOpen
    public void onOpen(@PathParam(value = "userId") String userId, Session session) {
        System.out.println("=============== onOpen ===============");
        clients.put(userId, session);

        //下线提示信息
        this.sendMsgAll(userId + "加入了群聊,当前剩余：" + clients.size() + "人");

        sessions.add(session);
    }

    //关闭
    @OnClose
    public void onClose(@PathParam(value = "userId") String userId, Session session) {
        System.out.println("=============== onClose ===============");
        try {
            sessions.remove(session);
            clients.remove(userId, session);

            //下线提示信息
            this.sendMsgAll(userId + "偷偷的跑了,当前剩余：" + clients.size() + "人");

        } catch (Exception e) {
            logger.error("onClose异常", e.getMessage());
        }
    }

    //接收消息   客户端发送过来的消息
    @OnMessage
    public void onMessage(@PathParam(value = "userId") String userId, String message, Session session) {
        System.out.println("=============== onMessage ===============");
        System.out.println("params: " + message);
        try {
            //校验参数
            if (StringUtil.isEmpty(message)) return; //参数不为空
            DataMap input = JSONObject.parseObject(message, DataMap.class);
            System.out.println(input);
            String req = input.get("req") + "";
            if ("0".equals(input.get("isMore"))) { //群发
                this.sendMsgAll(userId+"：群发消息：" + req);
            } else {//单独发送
                String[] str = req.split("_");
                for (Map.Entry<String, Session> entry : clients.entrySet()) {
                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                    //给指定的人，以及自己发送消息
                    if (entry.getKey().equals(str[0]) || entry.getKey().equals(userId)) {
                        this.sendMsgUser(entry.getValue(), userId+"：发送消息给：" + str[0] + ",内容：" + str[1]);
                    }
                }
            }
            System.out.println("当前在线人数：" + clients.size());
        } catch (Exception e) {
            logger.error("onMessage异常", e.getMessage());
            e.printStackTrace();
        }
    }

    //异常
    @OnError
    public void onError(@PathParam(value = "userId") String userId, Session session, Throwable throwable) {
        System.out.println("=============== onError ===============");
        try {
            logger.error("onError发生异常!", throwable);
            session.close();
            clients.remove(userId, session);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //统一的发送消息方法
    public synchronized static void sendMsgAll(String msg) {
        System.out.println("=============== sendMsgAll ===============");
        try {
            //数据压缩
            //byte[] compress = GZipUtils.compress(msg.getBytes());
            //给客户端返回消息
            for (Session s : sessions) {
                System.out.println("session : " + s.getId());
                s.getBasicRemote().sendText(msg);
            }
        } catch (Exception e) {
            logger.error("发送数据异常" + e.getMessage());
            e.printStackTrace();
        }
    }

    //指定用户发送
    public synchronized static void sendMsgUser(Session session, String msg) {
        System.out.println("=============== sendMsgUser ===============");
        try {
            //数据压缩
            //byte[] compress = GZipUtils.compress(msg.getBytes());
            //给客户端返回消息
            session.getBasicRemote().sendText(msg);
        } catch (Exception e) {
            logger.error("发送数据异常" + e.getMessage());
            e.printStackTrace();
        }
    }
}

