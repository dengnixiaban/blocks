package cn.blocks.userservice.controller;

import cn.blocks.commonutils.utils.LogUtils;
import cn.blocks.httpserver.annotation.AccessLog;
import cn.blocks.userapi.model.UserDTO;
import cn.blocks.userapi.service.mono.IUserService;
import cn.blocks.userservice.repository.po.UserPO;
import cn.blocks.userservice.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Optional;

/**
 * @description
 *          用户接口
 *
 * @author Somnus丶y
 * @date 2019/8/30 18:35
 */
@Api("用户服务")
@RestController
@Slf4j
public class UserController implements IUserService {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "用户查询")
    @Override
    public Mono<UserDTO> userInfo(@ModelAttribute UserDTO userDTO) {
        LogUtils.info(log,"访问user-info接口,入参%s",
                JSON.toJSONString(userDTO, SerializerFeature.DisableCircularReferenceDetect));
        UserPO userPO = userService.queryById(userDTO.getId());
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(userPO,dto);
        return Mono.just(Optional.ofNullable(userDTO).orElse(new UserDTO()));
    }



    @ApiOperation(value = "用户保存")
    @AccessLog(desc = "/user/save")
    @RequestMapping(value = "/save",method = RequestMethod.PUT)
    public Mono<UserDTO> user(@RequestBody(required = false)@Validated UserDTO userDTO){
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(userDTO,userPO);
        userPO.setCreateTime(new Date());
        userPO.setModifyTime(new Date());
        UserDTO dto = userService.saveOne(userPO);
        return Mono.just(Optional.ofNullable(dto).orElse(new UserDTO()));
    }

}
