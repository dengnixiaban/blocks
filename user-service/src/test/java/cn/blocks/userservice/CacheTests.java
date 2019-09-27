package cn.blocks.userservice;

import cn.blocks.commoncache.service.ICacheService;
import cn.blocks.userservice.cache.Test1CacheServiceImpl;
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
    private ICacheService<String,String> testCacheService;

    @Resource
    private Test1CacheServiceImpl test1CacheService;

    @Test
    public void test1(){
        String aaa = testCacheService.load("aaa");
        System.out.println(aaa);
    }


    @Test
    public void test2(){
        Object data = test1CacheService.getData("222");
        System.out.println(data);
    }

}
