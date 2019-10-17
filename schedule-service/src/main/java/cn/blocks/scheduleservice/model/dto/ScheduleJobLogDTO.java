package cn.blocks.scheduleservice.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <li>文件名称: 定时任务日志 实体类</li>
 */
@Data
@NoArgsConstructor
public class ScheduleJobLogDTO{


    /**
     * 任务日志id
     */
	private Long logId;


    /**
     * 任务id
     */
	private Long jobId;


    /**
     * 任务状态    0：已发起    1：成功   2-失败
     */
	private Integer status;

	/**
	 * 回执信息
	 */
	private String recepitMsg;

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
	private Long createTime;


	/**
	 * 回执时间
	 */
	private Long receiptTime;



}
