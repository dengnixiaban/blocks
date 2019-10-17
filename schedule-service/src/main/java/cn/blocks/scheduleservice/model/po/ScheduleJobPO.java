package cn.blocks.scheduleservice.model.po;

import cn.blocks.scheduleservice.model.dto.ScheduleJobDTO;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * <li>文件名称: 定时任务 实体类</li>
 */
@TableName("schedule_job")
@Data
@ToString
@NoArgsConstructor
public class ScheduleJobPO extends Model<ScheduleJobPO> {



    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
	@TableId(type = IdType.AUTO)
	private Long jobId;


	/**
	 * 任务描述
	 */
	private String desc;

	/**
	 * 创建人/负责人  json
	 */
	private String user;

	/**
	 * 执行bean
	 */
	@TableField("exec_bean")
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
	@TableField("notify_type")
	private Integer notifyType;

	/**
	 * 交换机类型 for rabbitmq 0-topic 1-direct 2-fanout 3-header
	 */
	@TableField("exchange_type")
	private Integer exchangeType;

	/**
	 * 交换机
	 */
	private String exchange;

	/**
	 * 路由键
	 */
	@TableField("routing_key")
	private String routingKey;


	/**
     * 任务状态  0：正常  1：暂停
     */
	private Integer status;


    /**
     * 创建时间
     */
	@TableField("create_time")
	private Long createTime;

	/**
	 * 修改时间
	 */
	@TableField("modify_time")
	private Long modifyTime;


	@Override
	protected Serializable pkVal() {
		return this.jobId;
	}


	public ScheduleJobPO(ScheduleJobDTO scheduleJob){
		if(Objects.nonNull(scheduleJob)){
			BeanUtils.copyProperties(scheduleJob,this);
			this.user = JSON.toJSONString(scheduleJob.getUser());
		}
	}

}
