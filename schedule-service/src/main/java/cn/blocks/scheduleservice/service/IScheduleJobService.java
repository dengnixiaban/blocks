package cn.blocks.scheduleservice.service;

import cn.blocks.scheduleservice.model.dto.ScheduleJobDTO;
import cn.blocks.scheduleservice.model.po.ScheduleJobPO;
import com.baomidou.mybatisplus.service.IService;

/**
 * <li>文件名称: 定时任务 服务接口</li>
 */
public interface IScheduleJobService extends IService<ScheduleJobPO> {

    void save(ScheduleJobDTO scheduleJob);

    void update(ScheduleJobDTO scheduleJob);

    void run(Long[] jobIds);

    void pause(Long[] jobIds);

    void resume(Long[] jobIds);
}
