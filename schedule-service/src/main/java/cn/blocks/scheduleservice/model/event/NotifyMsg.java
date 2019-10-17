package cn.blocks.scheduleservice.model.event;

import lombok.Data;

/**
 * @description
 *          通知消息体
 *
 * @auther Somnus丶y
 * @date 2019/10/17 10:57
 */
@Data
public class NotifyMsg {

    /**
     * 任务id
     */
    private String scheduleId;

    /**
     * 任务描述
     */
    private String desc;

    /**
     * 消息id
     */
    private String msgId;

    /**
     * 执行体
     */
    private String execBean;

    /**
     * 交换机
     */
    private String exchange;

    /**
     * 路由key
     */
    private String routKey;

    /**
     * 具体消息内容
     */
    private Object msg;



}
