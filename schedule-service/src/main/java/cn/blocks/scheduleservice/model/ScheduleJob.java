package cn.blocks.scheduleservice.model;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <li>文件名称: 定时任务 实体类</li>
 */
@TableName("schedule_job")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleJob extends Model<ScheduleJob> {

    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
	@TableId(type = IdType.AUTO)
	private Long jobId;

	/**
	 * 任务调度参数key
	 */
	@TableField(exist = false)
	public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";
    /**
     * 请求api
     */
	private String url;
    /**
     * 参数
     */
	private String params;
    /**
     * cron表达式
     */
	@TableField("cron_expression")
	@ApiModelProperty(value = "cron表达式")
	private String cronExpression;
    /**
     * 任务状态  0：正常  1：暂停
     */
	private Integer status;
    /**
     * 备注
     */
	private String remark;
    /**
     * 创建时间
     */
	@TableField("create_time")
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	private String exchange;

	@TableField("routing_key")
	@ApiModelProperty(value = "路由键")
	private String routingKey;


	@Override
	protected Serializable pkVal() {
		return this.jobId;
	}

}
