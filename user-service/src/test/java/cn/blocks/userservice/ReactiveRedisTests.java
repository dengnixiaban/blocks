package cn.blocks.userservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

/**
 * @description
 *          reactiveredis
 * @auther Somnus丶y
 * @date 2019/9/23 17:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactiveRedisTests {

    @Autowired
    private ReactiveRedisTemplate reactiveRedisTemplate;

    @Test
    public void test1(){
        Mono test = reactiveRedisTemplate.opsForValue().get("test");
        Object block = test.block();
        System.out.println(block);
    }

}
