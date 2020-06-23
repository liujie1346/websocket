package com.websocket.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 潘要东
 * @create 2018-04-02 13:33
 * @descriptions <p>.properties文件读取工具类</p>
 */
public final class PropertiesResourceUtil {

    private final static Logger log = LoggerFactory.getLogger(PropertiesResourceUtil.class);
    private final static String FilePath = ".properties";

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/27 15:30
     * @descriptions <p>加载Properties资源文件</p>
     * @params [clazz]
     * @return java.util.Properties
     ****************************************************************************************************/
    public final static Properties load(Class clazz) {
        Properties props = new Properties();
        // 获取资源文件路径
        String path = clazz.getName().replace('.', '/') + FilePath;
        // 打开资源文件
        InputStream is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(path);
        if (is == null) {
            log.error("Can not find '{}'.", path);
            return props;
        }
        // 加载资源文件
        try {
            props.load(is);
        } catch (IOException e) {
            log.error("Failed to load '{}'.", path, e);
        } finally {
            IoUtil.closeInputStream(is);
        }
        return props;
    }


    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/27 15:32
     * @descriptions <p>加载Properties资源文件</p>
     * @params [classpath]
     * @return java.util.Properties
     ****************************************************************************************************/
    public final static Properties load(String classpath) {
        Properties properties = new Properties();
        // 打开资源文件
        InputStream is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(classpath);
        if (is == null) {
            log.error("Can not find [ {} ] file.", classpath);
            return properties;
        }
        // 加载资源文件
        try {
            properties.load(is);
        } catch (IOException e) {
            log.error("Failed to load [ {} ]", classpath, e);
        } finally {
            IoUtil.closeInputStream(is);
        }
        return properties;
    }
}
