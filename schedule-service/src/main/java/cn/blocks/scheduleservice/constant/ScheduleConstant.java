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



}
