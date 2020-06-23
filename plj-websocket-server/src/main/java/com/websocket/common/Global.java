package com.websocket.common;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 潘要东
 * @create 2018-04-02 13:33
 * @descriptions <p>全局配置类</p>
 */
public final class Global {

    /**
     * 默认字符集
     */
    public final static Charset Charset = StandardCharsets.UTF_8;

    /**
     * 空字符串
     */
    public final static String Empty = StringUtils.EMPTY;

    /**
     * 空数据对象
     */
    public final static Object EmptyObject = new Object();

    /**
     * 空列表数据对象
     */
    public final static List<Object> EmptyListObject = new ArrayList<>();

}
