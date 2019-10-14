package cn.blocks.scheduleservice.quartz;

import cn.blocks.scheduleservice.model.ScheduleJob;
import cn.blocks.scheduleservice.model.ScheduleJobLog;
import cn.blocks.scheduleservice.service.IScheduleJobLogService;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * 定时任务
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月30日 下午12:44:21
 */
@Component
public class ScheduleJobTask extends QuartzJobBean {
	@Autowired
	private IScheduleJobLogService logService;

	private Logger logger = LoggerFactory.getLogger(getClass());
	private ExecutorService service = Executors.newSingleThreadExecutor(); 
	
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap()
        		.get(ScheduleJob.JOB_PARAM_KEY);
        

        
        //数据库保存执行记录
		ScheduleJobLog log = new ScheduleJobLog();
        log.setJobId(scheduleJob.getJobId());
        log.setUrl(scheduleJob.getUrl());
        log.setParams(scheduleJob.getParams());
        log.setCreateTime(LocalDateTime.now());

        //任务开始时间
        long startTime = System.currentTimeMillis();
        
        try {
            //执行任务 TODO 使用rabbit
//        	logger.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
//            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getUrl(),
//            		 scheduleJob.getParams());
//            Future<?> future = service.submit(task);
            
//			future.get();

			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int)times);
			//任务状态    0：成功    1：失败
			log.setStatus(0);
			
//			logger.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
		} catch (Exception e) {
			logger.error("##########################任务执行失败",e);
			e.printStackTrace();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(baos));
			String exception = baos.toString();
			try {
				baos.close();
			}catch (Exception e2){}

			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int)times);
			//任务状态    0：成功    1：失败
			log.setStatus(1);
			log.setError(StringUtils.substring("执行定时任务失败:"+exception, 0, 2000));
		}finally {
			logService.insert(log);
		}
    }
}
