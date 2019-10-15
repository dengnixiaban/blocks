package cn.blocks.webadmin;

import cn.blocks.webadmin.remote.fallback.RouteClientFallback;
import cn.blocks.webadmin.remote.reactive.PluginClient;
import cn.blocks.webadmin.remote.reactive.RouteClient;
import cn.blocks.webadmin.remote.reactive.ServiceClient;
import feign.Client;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactivefeign.webclient.WebReactiveFeign;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author Somnus丶y
 * @date 2019/8/31 9:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AsyncFeignClientTest {
//    public static final String SERVER = "http://USER-SERVICE:8001";
    public static final String SERVER = "http://127.0.0.1:8081";

    private CountDownLatch latch;

    private ServiceClient serviceClient;

    private RouteClient routeClient;

    private PluginClient pluginClient;

    @Autowired
    private Client feignClient;

    @Autowired
    private RouteClientFallback routeClientFallback;


    @Before
    public void setup(){
        latch= new CountDownLatch(3);
        String service= SERVER + "/user/user-info";
        serviceClient= WebReactiveFeign
                .<ServiceClient>builder()
                .target(ServiceClient.class, service);
        String route= SERVER + "/user/user-info";
        routeClient= WebReactiveFeign
                .<RouteClient>builder()
                .fallback(routeClientFallback)
                .target(RouteClient.class, route);
        String plugin= SERVER + "/user/user-info";
        pluginClient= WebReactiveFeign
                .<PluginClient>builder()
                .target(PluginClient.class, plugin);
    }

    @Test
    public void aggressionTest() throws InterruptedException {
        long current= System.currentTimeMillis();
        System.out.println("开始调用聚合查询");
        serviceTest();
        routeTest();
        pluginTest();
        latch.await(10, TimeUnit.SECONDS);
        System.out.println("调用聚合查询结束！耗时：" + (System.currentTimeMillis() - current) + "毫秒");
    }

    @Test
    public void serviceTest(){
        long current= System.currentTimeMillis();
        System.out.println("开始获取Service");
        serviceClient.list()
                .doOnError(e->{
                    e.printStackTrace();
                })
                .subscribe(result ->{
                    System.out.println(result);
                    latch.countDown();
                    System.out.println("获取Service结束！耗时：" + (System.currentTimeMillis() - current) + "毫秒");
                });
    }

    @Test
    public void routeTest(){
        long current= System.currentTimeMillis();
        System.out.println("开始获取Route");
        routeClient.list()
                .doOnError(e->{
                    e.printStackTrace();
                })
                .subscribe(result ->{
                    System.out.println(result);
                    latch.countDown();
                    System.out.println("获取Route结束！耗时：" + (System.currentTimeMillis() - current) + "毫秒");
                });
    }

    @Test
    public void pluginTest(){
        long current= System.currentTimeMillis();
        System.out.println("开始获取Plugin");
        pluginClient.list()
                .doOnError(e->{
                    e.printStackTrace();
                })
                .subscribe(result ->{
                    System.out.println(result);
                    latch.countDown();
                    System.out.println("获取Plugin结束！耗时：" + (System.currentTimeMillis() - current) + "毫秒");
                });

    }
}
