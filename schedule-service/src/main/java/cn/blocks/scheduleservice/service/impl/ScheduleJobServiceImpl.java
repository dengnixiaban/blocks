package cn.blocks.scheduleservice.service.impl;

import cn.blocks.commonutils.utils.LogUtils;
import cn.blocks.scheduleservice.constant.ScheduleConstant;
import cn.blocks.scheduleservice.mapper.ScheduleJobMapper;
import cn.blocks.scheduleservice.model.dto.ScheduleJobDTO;
import cn.blocks.scheduleservice.model.po.ScheduleJobPO;
import cn.blocks.scheduleservice.quartz.ScheduleUtils;
import cn.blocks.scheduleservice.service.IScheduleJobService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

/**
 * <li>文件名称: 定时任务 服务接口实现类</li>
 */
@Service("scheduleJobService")
@Transactional
@Slf4j
public class  ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper,ScheduleJobPO>
        implements IScheduleJobService {


    @Autowired
    private Scheduler scheduler;


    @PostConstruct
    public void init(){
        LogUtils.info(log,"初始化定时器");
        List<ScheduleJobPO> scheduleJobList = selectList(new EntityWrapper<>());
        scheduleJobList.stream()
                .filter(i->ScheduleConstant.SCHEDULESTATUS_START.equals(i.getStatus()))
                .map(ScheduleJobDTO::new)
                .forEach(i->{
                    CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, i.getJobId());
                    //如果不存在，则创建
                    if(Objects.isNull(cronTrigger)) {
                        ScheduleUtils.createScheduleJob(scheduler, i);
                    }else {
                        ScheduleUtils.updateScheduleJob(scheduler, i);
                    }
                });
    }

    /**
     * 创建任务,初始暂停态
     *
     * @param scheduleJob
     *              任务对象
     */
    @Override
    public void save(ScheduleJobDTO scheduleJob) {
        scheduleJob.setCreateTime(System.currentTimeMillis());
        scheduleJob.setStatus(ScheduleConstant.SCHEDULESTATUS_STOP);
        insert(new ScheduleJobPO(scheduleJob));
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }

    @Override
    public void update(ScheduleJobDTO scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
        update(new ScheduleJobPO(scheduleJob),
                new EntityWrapper<ScheduleJobPO>().eq("jobId",scheduleJob.getJobId()));
    }

    @Override
    public void run(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.run(scheduler, selectById(jobId));
        }
    }

    @Override
    public void pause(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.pauseJob(scheduler, jobId);
        }
        List<ScheduleJobPO> scheduleJobs = selectBatchIds(Lists.newArrayList(jobIds));
        scheduleJobs.forEach(s->s.setStatus(ScheduleConstant.SCHEDULESTATUS_STOP));
        updateBatchById(scheduleJobs,scheduleJobs.size());
    }

    @Override
    public void resume(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleUtils.resumeJob(scheduler, jobId);
        }
        List<ScheduleJobPO> scheduleJobs = selectBatchIds(Lists.newArrayList(jobIds));
        scheduleJobs.forEach(s->s.setStatus(ScheduleConstant.SCHEDULESTATUS_STOP));
        updateBatchById(scheduleJobs,scheduleJobs.size());
    }


}
