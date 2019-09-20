package cn.blocks.userservice;

import cn.blocks.userapi.model.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description
 *          redis test
 *
 * @author Somnus丶y
 * @date 2019/9/20 18:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate  redisTemplate;


    @Test
    public void test1(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(111L);
        redisTemplate.opsForValue().set("test",userDTO);
    }


}
