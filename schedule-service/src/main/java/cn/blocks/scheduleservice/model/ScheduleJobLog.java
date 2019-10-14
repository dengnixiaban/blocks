package cn.blocks.scheduleservice.model;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.io.Serializable;

/**
 * <li>文件名称: 定时任务日志 实体类</li>
 */
@TableName("schedule_job_log")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleJobLog extends Model<ScheduleJobLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 任务日志id
     */
	@TableId(type = IdType.AUTO)
	private Long logId;
    /**
     * 任务id
     */
	@TableField("job_id")
	@ApiModelProperty(value = "任务id")
	private Long jobId;
    /**
     * 请求api
     */
	private String url;
    /**
     * 参数
     */
	private String params;
    /**
     * 任务状态    0：成功    1：失败
     */
	private Integer status;
    /**
     * 失败信息
     */
	private String error;
    /**
     * 耗时(单位：毫秒)
     */
	private Integer times;
    /**
     * 创建时间
     */
	@TableField("create_time")
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;


	@Override
	protected Serializable pkVal() {
		return this.logId;
	}

}
