package cn.blocks.scheduleservice.quartz;

import cn.blocks.commonamqp.constant.ExchangeConstant;
import cn.blocks.scheduleservice.model.ScheduleJob;
import cn.blocks.scheduleservice.model.ScheduleJobLog;
import cn.blocks.scheduleservice.service.IScheduleJobLogService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * 定时任务
 */
@Component
@Slf4j
public class ScheduleJobTask extends QuartzJobBean {
	@Autowired
	private IScheduleJobLogService logService;
	@Autowired
	private AmqpTemplate amqpTemplate;

	private ExecutorService service = Executors.newFixedThreadPool(10);
	
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String json = (String) context.getMergedJobDataMap().get(ScheduleJob.JOB_PARAM_KEY);
		ScheduleJob scheduleJob = JSON.parseObject(json,ScheduleJob.class);

        //数据库保存执行记录
		ScheduleJobLog ScheduleJobLog = new ScheduleJobLog();
		ScheduleJobLog.setJobId(scheduleJob.getJobId());
		ScheduleJobLog.setUrl(scheduleJob.getUrl());
		ScheduleJobLog.setParams(scheduleJob.getParams());
		ScheduleJobLog.setCreateTime(LocalDateTime.now());

        //任务开始时间
        long startTime = System.currentTimeMillis();
        
        try {
            //执行任务
        	log.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
			Future<Object> submit = service.submit(() -> {
				Object message = amqpTemplate.convertSendAndReceive(scheduleJob.getExchange(),scheduleJob.getRoutingKey(),scheduleJob);
				return message;
			});
			//TODO 获取远程任务执行情况 后续处理
			Object message = submit.get();

			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			ScheduleJobLog.setTimes((int)times);
			//任务状态    0：成功    1：失败
			ScheduleJobLog.setStatus(0);

			log.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
		} catch (Exception e) {
			log.error("-------------------------------------任务执行失败",e);
			e.printStackTrace();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(baos));
			String exception = baos.toString();
			try {
				baos.close();
			}catch (Exception e2){}

			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			ScheduleJobLog.setTimes((int)times);
			//任务状态    0：成功    1：失败
			ScheduleJobLog.setStatus(1);
			ScheduleJobLog.setError(StringUtils.substring("执行定时任务失败:"+exception, 0, 2000));
		}finally {
			logService.insert(ScheduleJobLog);
		}
    }
}
