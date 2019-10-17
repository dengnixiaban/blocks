package cn.blocks.scheduleservice.model.dto;

import cn.blocks.scheduleservice.model.po.ScheduleJobPO;
import cn.blocks.userapi.model.UserDTO;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

/**
 * <li>文件名称: 定时任务 实体类</li>
 */
@Data
@NoArgsConstructor
public class ScheduleJobDTO{


    /**
     * 任务id
     */
	private Long jobId;

    /**
     * 任务描述
	 */
	private String desc;

    /**
     * 创建人/负责人  json
	 */
	private UserDTO user;

	/**
	 * 执行bean
	 */
	private String execBean;

    /**
     * 参数
     */
	private String params;

    /**
     * cron表达式
     */
	private String cron;

    /**
     * 通知方式  0-rabbitmq ,其他 todo
	 */
	private Integer notifyType;

	/**
     * 交换机类型 for rabbitmq 0-topic 1-direct 2-fanout 3-header
	 */
	private Integer exchangeType;

	/**
	 * 交换机
	 */
	private String exchange;

	/**
	 * 路由键
	 */
	private String routingKey;


	/**
     * 任务状态  0：正常  1：暂停
     */
	private Integer status;


    /**
     * 创建时间
     */
	private Long createTime;

	/**
     * 修改时间
	 */
	private Long modifyTime;


	public ScheduleJobDTO(ScheduleJobPO scheduleJob){
		if(Objects.nonNull(scheduleJob)){
			BeanUtils.copyProperties(scheduleJob,this);
			String user = scheduleJob.getUser();
			if(StringUtils.isNotEmpty(user)){
				this.user = JSON.parseObject(user,UserDTO.class);
			}
		}
	}


}
