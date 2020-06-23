package com.websocket.pojo;

import com.websocket.util.PropertiesResourceUtil;

import java.util.Properties;

/**
 * @author 王金波
 * @create 2018-04-27 18:59
 * @descriptions <p>加载global配置信息</p >
 */
public final class Global {

    public final static String huobiproHostWs; //

    public final static String huobiHadaxApi;
    public final static String huobiKline;
    public final static String huobiDepth;
    public final static String huobiTrade;
    public final static String huobiDetail;
    public final static String huobiTickers;

    public final static String binanceHost;
    public final static String binanceRestHost;
    public final static String binanceTrade;
    public final static String binanceDepth;
    public final static String binanceKlines;
    public final static String binance24hr;


    //系数
    public final static String erguPercentage;
    public final static String huoBiPercentage;
    public final static String binancePercentage;

    static {
        Properties props = PropertiesResourceUtil.load("global.properties");

        huobiproHostWs = props.getProperty("huobiproHostWs", com.websocket.common.Global.Empty);

        huobiHadaxApi = props.getProperty("huobiHadaxApi", com.websocket.common.Global.Empty);
        huobiKline = props.getProperty("huobiKline", com.websocket.common.Global.Empty);
        huobiDepth = props.getProperty("huobiDepth", com.websocket.common.Global.Empty);
        huobiTrade = props.getProperty("huobiTrade", com.websocket.common.Global.Empty);
        huobiDetail = props.getProperty("huobiDetail", com.websocket.common.Global.Empty);
        huobiTickers = props.getProperty("huobiTickers", com.websocket.common.Global.Empty);

        binanceHost = props.getProperty("binanceHost", com.websocket.common.Global.Empty);
        binanceRestHost = props.getProperty("binanceRestHost", com.websocket.common.Global.Empty);
        binanceTrade = props.getProperty("binanceTrade", com.websocket.common.Global.Empty);
        binanceDepth = props.getProperty("binanceDepth", com.websocket.common.Global.Empty);
        binanceKlines = props.getProperty("binanceKlines", com.websocket.common.Global.Empty);
        binance24hr = props.getProperty("binance24hr", com.websocket.common.Global.Empty);

        erguPercentage = props.getProperty("erguPercentage", com.websocket.common.Global.Empty);
        huoBiPercentage = props.getProperty("huoBiPercentage", com.websocket.common.Global.Empty);
        binancePercentage = props.getProperty("binancePercentage", com.websocket.common.Global.Empty);
    }
}
