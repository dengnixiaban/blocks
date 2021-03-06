package cn.blocks.commonmysql.model.po;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.*;

import java.util.Date;

/**
 * @description
 *          公共时间字段
 *
 * @author Somnus丶y
 * @date 2019/9/10 18:46
 */
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BaseTimePO {

    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("modifyTime")
    private Date modifyTime;

    /**
     * 删除时间(需要假删除时使用)
     */
    @TableField("deleteTime")
    private Date deleteTime;

    /**
     * 最后操作者id
     */
    @TableField("modifyId")
    private Long modifyId;

}
