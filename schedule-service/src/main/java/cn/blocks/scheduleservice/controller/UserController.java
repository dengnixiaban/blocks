package cn.blocks.scheduleservice.controller;

import cn.blocks.commonutils.model.BaseResp;
import cn.blocks.commonutils.model.HttpEnum;
import cn.blocks.httpserver.annotation.AccessLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 *          调度平台用户操作
 *
 * @auther Somnus丶y
 * @date 2019/10/14 12:24
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @AccessLog(desc = "/user/login")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResp login(){
        return new BaseResp(HttpEnum.SUCCESS);
    }


}
