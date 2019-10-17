package cn.blocks.scheduleservice.service.impl;
import cn.blocks.scheduleservice.mapper.ScheduleJobLogMapper;
import cn.blocks.scheduleservice.model.po.ScheduleJobLogPO;
import cn.blocks.scheduleservice.service.IScheduleJobLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <li>文件名称: 定时任务日志 服务接口实现类</li>
 */
@Service("scheduleJobLogService")
@Transactional
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLogPO> implements IScheduleJobLogService {

}
