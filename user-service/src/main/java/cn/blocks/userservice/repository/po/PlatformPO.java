package cn.blocks.userservice.repository.po;

import cn.blocks.commonmongodb.model.po.BaseTimePO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @description
 *          平台po
 *
 *
 * @auther Somnus丶y
 * @date 2019/10/14 16:40
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document("user_platform")
public class PlatformPO extends BaseTimePO {

    /**
     * 平台id
     */
    @Id
    private Long id;

    /**
     * 平台描述
     */
    private String desc;


}
