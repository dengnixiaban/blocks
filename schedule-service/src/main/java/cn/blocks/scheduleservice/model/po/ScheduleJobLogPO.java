package cn.blocks.scheduleservice.model.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * <li>文件名称: 定时任务日志 实体类</li>
 */
@TableName("schedule_job_log")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleJobLogPO extends Model<ScheduleJobLogPO> {

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
	private Long jobId;


    /**
     * 任务状态    0：已发起    1：成功   2-失败
     */
	private Integer status;


	/**
     * 失败信息
     */
	private String error;

	/**
	 * 回执信息
	 */
	private String recepitMsg;


	/**
     * 耗时(单位：毫秒)
     */
	private Integer times;


	/**
     * 创建时间
     */
	@TableField("create_time")
	private Long createTime;


	/**
	 * 回执时间
	 */
	@TableField("receipt_time")
	private Long receiptTime;



	@Override
	protected Serializable pkVal() {
		return this.logId;
	}


}
