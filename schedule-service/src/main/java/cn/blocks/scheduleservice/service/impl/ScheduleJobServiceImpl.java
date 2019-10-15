package cn.blocks.scheduleservice.service.impl;
import cn.blocks.scheduleservice.constant.ScheduleConstant;
import cn.blocks.scheduleservice.mapper.ScheduleJobLogMapper;
import cn.blocks.scheduleservice.mapper.ScheduleJobMapper;
import cn.blocks.scheduleservice.model.ScheduleJob;
import cn.blocks.scheduleservice.quartz.ScheduleUtils;
import cn.blocks.scheduleservice.service.IScheduleJobService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.checkerframework.checker.units.qual.A;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <li>文件名称: 定时任务 服务接口实现类</li>
 */
@Service
@Transactional
@Slf4j
public class  ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements IScheduleJobService {
    @Resource
    private ScheduleJobMapper scheduleJobMapper;
    @Autowired
    private Scheduler scheduler;


    @PostConstruct
    public void init(){
        log.info("初始化定时器");
        List<ScheduleJob> scheduleJobList = scheduleJobMapper.selectList(new EntityWrapper<>());
        for(ScheduleJob scheduleJob : scheduleJobList){
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    public void save(ScheduleJob scheduleJob) {
        scheduleJob.setCreateTime(LocalDateTime.now());
        scheduleJob.setStatus(ScheduleConstant.SCHEDULESTATUS_START);
        scheduleJobMapper.insert(scheduleJob);
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }

    public void update(ScheduleJob scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
        scheduleJobMapper.update(scheduleJob,new EntityWrapper<ScheduleJob>().eq("jobId",scheduleJob.getJobId()));
    }

    public void run(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.run(scheduler, this.selectById(jobId));
        }
    }

    public void pause(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.pauseJob(scheduler, jobId);
        }
        List<ScheduleJob> scheduleJobs = this.selectBatchIds(Lists.newArrayList(jobIds));
        scheduleJobs.forEach(s->s.setStatus(ScheduleConstant.SCHEDULESTATUS_STOP));
        updateBatchById(scheduleJobs,scheduleJobs.size());
    }

    public void resume(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.resumeJob(scheduler, jobId);
        }
        List<ScheduleJob> scheduleJobs = this.selectBatchIds(Lists.newArrayList(jobIds));
        scheduleJobs.forEach(s->s.setStatus(ScheduleConstant.SCHEDULESTATUS_STOP));
        updateBatchById(scheduleJobs,scheduleJobs.size());
    }
}
