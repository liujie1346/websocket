package com.websocket.pojo;

import com.websocket.common.MessageUtils;

import java.io.Serializable;

/**
 * @author 潘要东
 * @create 2018-04-02 13:33
 * @descriptions <p>返回结果对象工具类</p>
 */
public class Result implements Serializable {

    private Object data = null;
    private String msg;
    private String code;
    private String err = "";

    public Result() {
        this.code = "0000";
        this.msg = MessageUtils.getValue(code);
    }

    public Result(String code) {
        this.code = code;
        this.msg = MessageUtils.getValue(code);
    }

    public Result(String msg, String code, String err) {
        this.msg = msg;
        this.code = code;
        this.err = err;
    }

    public Result(Object data, String msg, String code, String err) {
        this.data = data;
        this.msg = msg;
        this.code = code;
        this.err = err;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        setMsg(MessageUtils.getValue(code));
        this.code = code;
    }

    public void setCode(String code, Object... objects) {
        setMsg(MessageUtils.getValue(code, objects));
        this.code = code;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", err='" + err + '\'' +
                '}';
    }
}
