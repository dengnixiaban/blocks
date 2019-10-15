package cn.blocks.scheduleservice.service;

import cn.blocks.scheduleservice.model.ScheduleJob;
import com.baomidou.mybatisplus.service.IService;

/**
 * <li>文件名称: 定时任务 服务接口</li>
 */
public interface IScheduleJobService extends IService<ScheduleJob> {
    void save(ScheduleJob scheduleJob);

    void update(ScheduleJob scheduleJob);

    void run(Long[] jobIds);

    void pause(Long[] jobIds);

    void resume(Long[] jobIds);
}
