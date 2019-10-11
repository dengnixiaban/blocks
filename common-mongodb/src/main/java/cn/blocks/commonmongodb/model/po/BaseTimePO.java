package cn.blocks.commonmongodb.model.po;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

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
    @Field("createTime")
    private Date createTime;

    /**
     * 修改时间
     */
    @Field("modifyTime")
    private Date modifyTime;

    /**
     * 删除时间(需要假删除时使用)
     */
    @Field("deleteTime")
    private Date deleteTime;

    /**
     * 最后操作者id
     */
    @Field("modifyId")
    private Long modifyId;

}
