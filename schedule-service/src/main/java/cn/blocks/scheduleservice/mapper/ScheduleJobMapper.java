package cn.blocks.scheduleservice.mapper;

import cn.blocks.scheduleservice.model.ScheduleJob;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <li>文件名称: 定时任务 Mapper 接口</li>
 */
@Mapper
public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {

}