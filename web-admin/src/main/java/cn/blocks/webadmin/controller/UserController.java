package cn.blocks.webadmin.controller;

import cn.blocks.userapi.model.UserDTO;
import cn.blocks.webadmin.remote.normal.FeignUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/8/30 18:56
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private FeignUserService userService;


    @RequestMapping(value = "/user-info",method = RequestMethod.GET)
    Mono<UserDTO> userInfo(UserDTO userDTO){
        UserDTO userDTOMono = userService.userInfo(userDTO);
        return Mono.justOrEmpty(userDTOMono);
    }

}
