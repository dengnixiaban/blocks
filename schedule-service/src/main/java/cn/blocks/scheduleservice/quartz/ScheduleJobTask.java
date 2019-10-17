package cn.blocks.scheduleservice.quartz;

import cn.blocks.commonutils.utils.LogUtils;
import cn.blocks.scheduleservice.constant.ScheduleConstant;
import cn.blocks.scheduleservice.model.dto.ScheduleJobDTO;
import cn.blocks.scheduleservice.model.event.NotifyMsg;
import cn.blocks.scheduleservice.model.po.ScheduleJobLogPO;
import cn.blocks.scheduleservice.notify.NotifyStrategy;
import cn.blocks.scheduleservice.service.IScheduleJobLogService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.concurrent.RejectedExecutionException;

/**
 * 定时任务
 */
@Component
@Slf4j
public class ScheduleJobTask extends QuartzJobBean {

	@Autowired
	private IScheduleJobLogService scheduleJobLogService;

	@Autowired
	private NotifyStrategy notifyStrategy;


    @Override
    protected void executeInternal(JobExecutionContext context) {
		String json = (String) context.getMergedJobDataMap().get(ScheduleConstant.JOB_PARAM_KEY);
		ScheduleJobDTO scheduleJob = JSON.parseObject(json, ScheduleJobDTO.class);

        //数据库保存执行记录
		ScheduleJobLogPO ScheduleJobLog = new ScheduleJobLogPO();
		ScheduleJobLog.setJobId(scheduleJob.getJobId());
		ScheduleJobLog.setCreateTime(System.currentTimeMillis());

        //任务开始时间
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
        try {
            //执行任务
			LogUtils.info(log,"任务准备执行，任务ID:%s",scheduleJob.getJobId());
			NotifyMsg notifyMsg = new NotifyMsg();
			notifyMsg.setExchange(scheduleJob.getExchange());
			notifyMsg.setRoutKey(scheduleJob.getRoutingKey());
			notifyMsg.setMsg(scheduleJob);
			notifyStrategy.notify(notifyMsg);
			//任务执行总时长
			stopWatch.stop();
			long times = stopWatch.getTotalTimeMillis();
			ScheduleJobLog.setTimes((int)times);
			//任务状态    0：成功    1：失败
			ScheduleJobLog.setStatus(ScheduleConstant.SCHEDULE_EXECTYPE_SEND);
			log.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
		}catch (Exception e) {
        	if(e.getClass().equals(RejectedExecutionException.class)){
				LogUtils.error(log,e,"线程池队列已满-任务执行失败");
			}else{
				LogUtils.error(log,e,"其他异常-任务执行失败");
			}
			//任务执行总时长
			stopWatch.stop();
			long times = stopWatch.getTotalTimeMillis();
			ScheduleJobLog.setTimes((int)times);
			//任务状态    0：已发起    1：成功   2-失败
			ScheduleJobLog.setStatus(ScheduleConstant.SCHEDULE_EXECTYPE_FAIL);
			ScheduleJobLog.setError(e.getMessage());
		}finally {
			scheduleJobLogService.insert(ScheduleJobLog);
		}


    }
}
