package cn.blocks.scheduleservice.config;

import cn.blocks.commonutils.model.BlocksThreadFactory;
import cn.blocks.scheduleservice.constant.ScheduleConstant;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description
 *          调度平台配置
 *
 * @auther Somnus丶y
 * @date 2019/10/17 15:06
 */
@Data
@Configuration
public class ScheduleConfig {


    @Value("${blocks.schedule.execPoolSize:10}")
    private Integer poolSize;

    @Value("${blocks.schedule.execQueueSize:10240}")
    private Integer queueSize;


    @Bean
    public ExecutorService scheExecutors(){
        return new ThreadPoolExecutor(poolSize, poolSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue(queueSize),new BlocksThreadFactory(ScheduleConstant.SCHEDULE_SERVICENAME),
                new ThreadPoolExecutor.AbortPolicy());
    }

}
