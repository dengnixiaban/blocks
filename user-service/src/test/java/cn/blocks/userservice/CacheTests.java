package cn.blocks.userservice;

import cn.blocks.userservice.cache.CaffeineCacheServiceImpl;
import cn.blocks.userservice.cache.GuavaCacheServiceImpl;
import cn.blocks.userservice.cache.RedisCacheServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/9/26 17:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheTests {

    @Resource
    private GuavaCacheServiceImpl guavaCacheService;

    @Resource
    private CaffeineCacheServiceImpl caffeineCacheService;

    @Resource
    private RedisCacheServiceImpl redisCacheService;



    @Test
    public void test2(){
        Object data1 = guavaCacheService.getData("111");
        System.out.println(data1);

        Object data2 = caffeineCacheService.getData("222");
        System.out.println(data2);

        Object data3 = redisCacheService.getData("333");
        System.out.println(data3);
    }

}
