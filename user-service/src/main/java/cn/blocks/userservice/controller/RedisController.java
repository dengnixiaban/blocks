package cn.blocks.userservice.controller;

import cn.blocks.userapi.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @description
 *          redis操作测试
 *
 * @author Somnus丶y
 * @date 2019/9/20 18:31
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/save")
    public Mono<UserDTO> save(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(111L);
        redisTemplate.opsForValue().set("test",userDTO);
        return Mono.just(userDTO);
    }

    @RequestMapping("/get")
    public Mono<UserDTO> get(){
        UserDTO userDTO = (UserDTO) redisTemplate.opsForValue().get("test");
        return Mono.just(userDTO);
    }

}
