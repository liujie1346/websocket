package com.websocket.common;

import com.alibaba.fastjson.JSON;
import com.websocket.pojo.Result;

import java.util.Objects;

/**
 * @author 潘要东
 * @create 2018-04-27 15:41
 * @descriptions <p>构建返回信息</p>
 */
public class ResultUtil {

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/27 16:04
     * @descriptions <p>构建成功返回对象</p>
     * @params [result, code]
     * @return com.ergu.vctapi.pojo.Result
     ****************************************************************************************************/
    public static String buildSuccessResult(Result result){
        result.setData(Objects.nonNull(result.getData()) ? result.getData() : Global.EmptyObject);
        return JSON.toJSONString(result);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/27 16:04
     * @descriptions <p>构建失败返回对象</p>
     * @params [result, code, error]
     * @return com.ergu.vctapi.pojo.Result
     ****************************************************************************************************/
    public static String buildErrorResult(Result result, String code, String error){
        result.setCode(code);
        result.setData(Global.EmptyObject);
        result.setErr(error);
        return JSON.toJSONString(result);
    }
    public static String buildErrorResult(Result result, String code){
        result.setCode(code);
        result.setData(Global.EmptyObject);
        result.setErr(Global.Empty);
        return JSON.toJSONString(result);
    }
    public static String buildErrorResult(Result result, String code, Object... objects){
        result.setCode(code, objects);
        result.setData(Global.EmptyObject);
        result.setErr(Global.Empty);
        return JSON.toJSONString(result);
    }

    /**********************************************************************.******************************
     * @author 潘要东
     * @create 2018/4/27 16:04
     * @descriptions <p>构建成功返回对象集合</p>
     * @params [result, code]
     * @return com.ergu.vctapi.pojo.Result
     ****************************************************************************************************/
    public static String buildSuccessResultList(Result result){
        result.setData(Objects.nonNull(result.getData()) ? result.getData() : Global.EmptyListObject);
        return JSON.toJSONString(result);
    }

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/27 16:04
     * @descriptions <p>构建失败返回对象集合</p>
     * @params [result, code, error]
     * @return com.ergu.vctapi.pojo.Result
     ****************************************************************************************************/
    public static String buildErrorResultList(Result result, String code, String error){
        result.setCode(code);
        result.setData(Global.EmptyListObject);
        result.setErr(error);
        return JSON.toJSONString(result);
    }
    public static String buildErrorResultList(Result result, String code){
        result.setCode(code);
        result.setData(Global.EmptyListObject);
        result.setErr(Global.Empty);
        return JSON.toJSONString(result);
    }
    public static String buildErrorResultList(Result result, String code, Object... objects){
        result.setCode(code, objects);
        result.setData(Global.EmptyListObject);
        result.setErr(Global.Empty);
        return JSON.toJSONString(result);
    }


    public static String buildErrorResultPage(Result result, String code, Object... objects){
        result.setCode(code, objects);
        result.setData(result.getData());
        result.setErr(Global.Empty);
        return JSON.toJSONString(result);
    }
}
