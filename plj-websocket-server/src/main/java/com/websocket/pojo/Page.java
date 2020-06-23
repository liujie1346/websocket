package com.websocket.pojo;


import com.websocket.util.DataMap;

/**
 * @author 潘要东
 * @create 2018-04-02 13:33
 * @descriptions <p>分页对象类</p>
 */
public class Page {

    private int pageNum = 1;
    private int pageSize = 10;
    private DataMap dataMap = new DataMap();

    public DataMap getDataMap() {
        return dataMap;
    }

    public void setDataMap(DataMap dataMap) {
        this.dataMap = dataMap;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
