package com.websocket.util;

import com.alibaba.fastjson.JSON;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * @author 潘要东
 * @create 2018-04-02 13:33
 * @descriptions <p>发送http请求模版工具类</p>
 */
public class HttpUtil {

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:09
     * @descriptions <p>发送http请求</p>
     * @params [url, mediaType, httpMethod, obj, T]
     * @return T
     ****************************************************************************************************/
    private static <T> T sendHttp(String url, MediaType mediaType, HttpMethod httpMethod, Object obj, MultiValueMap header, Class<T> T) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);
        if (Objects.nonNull(header)){
            httpHeaders.addAll(header);
        }
        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(obj), httpHeaders);
        ResponseEntity<T> result = restTemplate.exchange(url, httpMethod, entity, T);
        return result.getBody();
    }

    public static <T> T sendHttps(String url, MediaType mediaType, HttpMethod httpMethod, DataMap obj, MultiValueMap header, Class<T> T) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(mediaType);
        if (Objects.nonNull(header)){
            httpHeaders.addAll(header);
        }
        HttpEntity<DataMap> entity = new HttpEntity<>(obj, httpHeaders);
        ResponseEntity<T> result = restTemplate.exchange(url, httpMethod, entity, T);
        return result.getBody();
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:12
     * @descriptions <p>发送json格式http请求</p>
     * @params [url, httpMethod, obj, T]
     * @return T
     ****************************************************************************************************/
    private static <T> T sendJsonHttp(String url, HttpMethod httpMethod, Object obj, MultiValueMap header, Class<T> T) {
        return sendHttp(url, MediaType.APPLICATION_JSON_UTF8, httpMethod, obj, header, T);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:12
     * @descriptions <p>发送form格式http请求</p>
     * @params [url, httpMethod, obj, T]
     * @return T
     ****************************************************************************************************/
    private static <T> T sendFormHttp(String url, HttpMethod httpMethod, Object obj, MultiValueMap header, Class<T> T) {
        return sendHttp(url, MediaType.APPLICATION_FORM_URLENCODED, httpMethod, obj, header, T);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:12
     * @descriptions <p>发送json格式http GET请求</p>
     * @params [url, httpMethod, obj, T]
     * @return T
     ****************************************************************************************************/
    public static <T> T sendJsonHttpGet(String url, MultiValueMap header, Class<T> T) {
        return sendJsonHttp(url, HttpMethod.GET, null, header, T);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:12
     * @descriptions <p>发送json格式http POST请求</p>
     * @params [url, httpMethod, obj, T]
     * @return T
     ****************************************************************************************************/
    public static <T> T sendJsonHttpPost(String url, Object obj, MultiValueMap header, Class<T> T) {
        return sendJsonHttp(url, HttpMethod.POST, obj, header, T);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:12
     * @descriptions <p>发送json格式http PUT请求</p>
     * @params [url, httpMethod, obj, T]
     * @return T
     ****************************************************************************************************/
    public static <T> T sendJsonHttpPut(String url, Object obj, MultiValueMap header, Class<T> T) {
        return sendJsonHttp(url, HttpMethod.PUT, obj, header, T);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:12
     * @descriptions <p>发送json格式http DELETE请求</p>
     * @params [url, httpMethod, obj, T]
     * @return T
     ****************************************************************************************************/
    public static <T> T sendJsonHttpDelete(String url, Object obj, MultiValueMap header, Class<T> T) {
        return sendJsonHttp(url, HttpMethod.DELETE, obj, header, T);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:12
     * @descriptions <p>发送form格式http GET请求</p>
     * @params [url, httpMethod, obj, T]
     * @return T
     ****************************************************************************************************/
    public static <T> T sendFromHttpGet(String url, MultiValueMap header, Class<T> T) {
        return sendFormHttp(url, HttpMethod.GET, null, header, T);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:12
     * @descriptions <p>发送form格式http POST请求</p>
     * @params [url, httpMethod, obj, T]
     * @return T
     ****************************************************************************************************/
    public static <T> T sendFromHttpPost(String url, Object obj, MultiValueMap header, Class<T> T) {
        return sendFormHttp(url, HttpMethod.POST, obj, header, T);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:12
     * @descriptions <p>发送form格式http PUT请求</p>
     * @params [url, httpMethod, obj, T]
     * @return T
     ****************************************************************************************************/
    public static <T> T sendFromHttpPut(String url, Object obj, MultiValueMap header, Class<T> T) {
        return sendFormHttp(url, HttpMethod.PUT, obj, header, T);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:12
     * @descriptions <p>发送form格式http DELETE请求</p>
     * @params [url, httpMethod, obj, T]
     * @return T
     ****************************************************************************************************/
    public static <T> T sendFromHttpDelete(String url, Object obj, MultiValueMap header, Class<T> T) {
        return sendFormHttp(url, HttpMethod.DELETE, obj, header, T);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:12
     * @descriptions <p>发送json格式http GET请求</p>
     * @params [url, obj]
     * @return com.ergu.vct.util.DataMap
     ****************************************************************************************************/
    public static DataMap sendJsonHttpGet(String url, MultiValueMap header) {
        return sendJsonHttp(url, HttpMethod.GET, null, header, DataMap.class);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:12
     * @descriptions <p>发送json格式http POST请求</p>
     * @params [url, obj]
     * @return com.ergu.vct.util.DataMap
     ****************************************************************************************************/
    public static DataMap sendJsonHttpPost(String url, Object obj, MultiValueMap header) {
        return sendJsonHttp(url, HttpMethod.POST, obj, header, DataMap.class);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:12
     * @descriptions <p>发送json格式http PUT请求</p>
     * @params [url, obj]
     * @return com.ergu.vct.util.DataMap
     ****************************************************************************************************/
    public static DataMap sendJsonHttpPut(String url, Object obj, MultiValueMap header) {
        return sendJsonHttp(url, HttpMethod.PUT, obj, header, DataMap.class);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:12
     * @descriptions <p>发送json格式http DELETE请求</p>
     * @params [url, obj]
     * @return com.ergu.vct.util.DataMap
     ****************************************************************************************************/
    public static DataMap sendJsonHttpDelete(String url, Object obj, MultiValueMap header) {
        return sendJsonHttp(url, HttpMethod.DELETE, obj, header, DataMap.class);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:12
     * @descriptions <p>发送form格式http GET请求</p>
     * @params [url, obj]
     * @return com.ergu.vct.util.DataMap
     ****************************************************************************************************/
    public static DataMap sendFromHttpGet(String url, MultiValueMap header) {
        return sendFormHttp(url, HttpMethod.GET, null, header, DataMap.class);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:12
     * @descriptions <p>发送form格式http POST请求</p>
     * @params [url, obj]
     * @return com.ergu.vct.util.DataMap
     ****************************************************************************************************/
    public static DataMap sendFromHttpPost(String url, Object obj, MultiValueMap header) {
        return sendFormHttp(url, HttpMethod.POST, obj, header, DataMap.class);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:12
     * @descriptions <p>发送form格式http PUT请求</p>
     * @params [url, obj]
     * @return com.ergu.vct.util.DataMap
     ****************************************************************************************************/
    public static DataMap sendFromHttpPut(String url, Object obj, MultiValueMap header) {
        return sendFormHttp(url, HttpMethod.PUT, obj, header, DataMap.class);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/2 16:12
     * @descriptions <p>发送form格式http DELETE请求</p>
     * @params [url, obj]
     * @return com.ergu.vct.util.DataMap
     ****************************************************************************************************/
    public static DataMap sendFromHttpDelete(String url, Object obj, MultiValueMap header) {
        return sendFormHttp(url, HttpMethod.DELETE, obj, header, DataMap.class);
    }

}
