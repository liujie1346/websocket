package com.websocket.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

/**
 * @author 潘要东
 * @create 2018-03-21 10:28
 * @descriptions <p>map对象工具类</p>
 */
public class DataMap extends HashMap implements Map, Serializable {

    Map map = null;
    HttpServletRequest request;

    public DataMap(HttpServletRequest request) {
        this.request = request;
        Map properties = request.getParameterMap();
        Map returnMap = new HashMap();

        Iterator entries = properties.entrySet().iterator();
        Entry entry;

        String name = "";
        String value = "";

        while (entries.hasNext()) {
            entry = (Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();

            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                StringBuffer valueSB = new StringBuffer();
                for (int i = 0; i < values.length; i++) {
                    valueSB.append(values[i]).append(",");
                }
                value = valueSB.substring(0, valueSB.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        map = returnMap;
    }

    public DataMap() {
        map = new HashMap();
    }

    public DataMap(Map map) {
        this.map = map;
    }

    @Override
    public Object get(Object key) {
        Object obj = null;
        if (map.get(key) instanceof Object[]) {
            Object[] objArr = (Object[]) map.get(key);
            obj = request == null ? objArr : (request.getParameter((String) key) == null ? objArr : objArr[0]);
        } else {
            obj = map.get(key);
        }
        return obj;
    }

    public String getString(Object key) {
        return String.valueOf(get(key));
    }

    public Integer getInteger(Object key) {
        Integer result = -1;
        try {
            result = Integer.parseInt(getString(key));
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public Object put(Object key, Object value) {
        return map.put(key, value);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Set entrySet() {
        return map.entrySet();
    }

    @Override
    public Set keySet() {
        return map.keySet();
    }

    @Override
    public void putAll(Map m) {
        map.putAll(m);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Collection values() {
        return map.values();
    }

    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }
}
