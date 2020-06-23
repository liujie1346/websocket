package com.websocket.util;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

/**
 * @author 潘要东
 * @create 2018-04-02 13:33
 * @descriptions <p>分页工具类</p>
 */
public class PageUtil {

    /****************************************************************************************************
     * @author 潘要东
     * @create 2018/4/24 11:36
     * @descriptions <p>构建分页返回信息</p>
     * @params [pageInfo]
     * @return com.alibaba.fastjson.JSONObject
     ****************************************************************************************************/
    public static JSONObject build(PageInfo pageInfo) {
        JSONObject object = new JSONObject();
        JSONObject pi = new JSONObject();
        pi.put("pageNum", pageInfo.getPageNum());//当前页
        pi.put("pageSize", pageInfo.getPageSize());//每页的数量
        pi.put("size", pageInfo.getSize());//当前页的数量
//        pi.put("startRow", pageInfo.getStartRow());//开始记录数
//        pi.put("endRow", pageInfo.getEndRow());//结束记录数
        pi.put("total", pageInfo.getTotal());//总记录数
        pi.put("pages", pageInfo.getPages());//总页数
//        pi.put("prePage", pageInfo.getPrePage());//前一页
//        pi.put("nextPage", pageInfo.getNextPage());//下一页
//        pi.put("isFirstPage", pageInfo.isIsFirstPage());//是否为第一页
//        pi.put("isLastPage", pageInfo.isIsLastPage());//是否为最后一页
//        pi.put("hasPreviousPage", pageInfo.isHasPreviousPage());//是否有前一页
//        pi.put("hasNextPage", pageInfo.isHasNextPage());//是否有下一页
//        pi.put("navigatePages", pageInfo.getNavigatePages());//导航页码数
//        pi.put("navigatepageNums", pageInfo.getNavigatepageNums());//所有导航页号
//        pi.put("navigateFirstPage", pageInfo.getNavigateFirstPage());//导航第一页
//        pi.put("navigateLastPage", pageInfo.getNavigateLastPage());//导航最后一页
//        pi.put("firstPage", pageInfo.getNavigateFirstPage());//第一页
//        pi.put("lastPage", pageInfo.getNavigateLastPage());//最后一页
        object.put("list", pageInfo.getList());
        object.put("pageination", pi);
        return object;
    }
}
