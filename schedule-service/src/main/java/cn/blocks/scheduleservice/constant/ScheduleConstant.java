package cn.blocks.scheduleservice.constant;

/**
 * @description
 *          调度中心常量
 *
 * @auther Somnus丶y
 * @date 2019/10/8 9:47
 */
public class ScheduleConstant {

    /**
     * 平台名称
     */
    public static final String SCHEDULE_SERVICENAME = "schedule-service";

    /**
     * 单机topic方式
     */
    public static final Integer SCHEDULETYPE_TOPIC = 0;

    /**
     * 全部广播方式
     */
    public static final Integer SCHEDULETYPE_FANOUT = 1;

    /**
     * 停止状态
     */
    public static final Integer SCHEDULESTATUS_STOP = 0;

    /**
     * 启动状态
     */
    public static final Integer SCHEDULESTATUS_START = 1;


    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    /**
     * 任务执行状态-已发送
     */
    public static final Integer SCHEDULE_EXECTYPE_SEND = 0;

    /**
     * 任务执行状态-成功
     */
    public static final Integer SCHEDULE_EXECTYPE_SUCCESS = 1;

    /**
     * 任务执行状态-失败
     */
    public static final Integer SCHEDULE_EXECTYPE_FAIL = 2;

    /**
     * job名称前缀
     */
    public static final String JOB_NAME = "TASK_";


}
