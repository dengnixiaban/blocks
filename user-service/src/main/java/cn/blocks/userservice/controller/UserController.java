package cn.blocks.userservice.controller;

import cn.blocks.userapi.model.UserDTO;
import cn.blocks.userapi.service.IUserService;
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
public class UserController implements IUserService {

    @Override
    public Mono<UserDTO> userInfo(UserDTO userDTO) {
        return Mono.just(Optional.ofNullable(userDTO).orElse(new UserDTO("1","1",new Date())));
    }

}
