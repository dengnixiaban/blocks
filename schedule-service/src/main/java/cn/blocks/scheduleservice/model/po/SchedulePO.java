package cn.blocks.scheduleservice.model.po;

import cn.blocks.commonmongodb.model.po.BaseTimePO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/10/8 9:37
 */
@Data
@Document(value = "schedule")
public class SchedulePO extends BaseTimePO {

    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 描述
     */
    private String desc;

    /**
     * cron
     */
    private String cron;

    /**
     * 调度方式
     */
    private Integer type;

    /**
     * 当前状态
     */
    private Integer status;




}
