package cn.blocks.scheduleservice.service.impl;
import cn.blocks.scheduleservice.constant.ScheduleConstant;
import cn.blocks.scheduleservice.mapper.ScheduleJobLogMapper;
import cn.blocks.scheduleservice.mapper.ScheduleJobMapper;
import cn.blocks.scheduleservice.model.ScheduleJob;
import cn.blocks.scheduleservice.quartz.ScheduleUtils;
import cn.blocks.scheduleservice.service.IScheduleJobService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.assertj.core.util.Lists;
import org.checkerframework.checker.units.qual.A;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <li>文件名称: 定时任务 服务接口实现类</li>
 */
@Service
@Transactional
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements IScheduleJobService {
    @Autowired
    private ScheduleJobMapper scheduleJobMapper;
    @Autowired
    private ScheduleJobLogMapper scheduleJobLogMapper;

    @Autowired
    private Scheduler scheduler;



    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init(){
        System.out.println("项目启动时，初始化定时器");
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

        updateBatchById(this.selectBatchIds(Lists.newArrayList(jobIds)), ScheduleConstant.SCHEDULESTATUS_STOP);
    }

    public void resume(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.resumeJob(scheduler, jobId);
        }

        updateBatchById(this.selectBatchIds(Lists.newArrayList(jobIds)), ScheduleConstant.SCHEDULESTATUS_START);
    }
}
