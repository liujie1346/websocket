package com.websocket.controller;

import com.websocket.common.BaseController;
import com.websocket.pojo.User;
import com.websocket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/user",produces = "application/json;charset=UTF-8")
public class UserController extends BaseController {


    @Autowired
    private UserService userService;

//    /****************************************************************************************************
//     * @author 庞留杰
//     * @create 2018-6-23 16:00:23
//     * @descriptions <p>24小时--请求</p >
//     ****************************************************************************************************/
//    @ApiResponses({@ApiResponse(code =200, message = "操作成功"),
//            @ApiResponse(code = 300 ,message = "参数异常"),
//            @ApiResponse(code =404, message = "路径错误"),
//            @ApiResponse(code =500, message = "服务器内部异常") })
//    @ApiOperation(value="24小时历史", notes="交易对:btcusdt,bchusdt,ltcusdt...")
//    @ApiImplicitParams({@ApiImplicitParam(paramType = "query",name = "symbol", value = "交易对", required = true, dataType = "String")})



    @GetMapping("findAll")
    public List<User> findAll() throws Exception{
        log.info("查询所有用户");
        return userService.findAll();
    }

}

