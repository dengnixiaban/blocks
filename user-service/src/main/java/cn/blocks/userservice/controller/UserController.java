package cn.blocks.userservice.controller;

import cn.blocks.commonutils.utils.LogUtils;
import cn.blocks.userapi.model.UserDTO;
import cn.blocks.userapi.service.mono.IUserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Optional;

/**
 * @description
 *          用户接口
 *
 * @auther Somnus丶y
 * @date 2019/8/30 18:35
 */
@RestController
@Slf4j
public class UserController implements IUserService {


    @Override
    public Mono<UserDTO> userInfo(@ModelAttribute UserDTO userDTO) {
        LogUtils.info(log,"访问user-info接口,入参%s",
                JSON.toJSONString(userDTO, SerializerFeature.DisableCircularReferenceDetect));
        return Mono.just(Optional.ofNullable(userDTO).orElse(new UserDTO("1","1",new Date())));
    }


}
