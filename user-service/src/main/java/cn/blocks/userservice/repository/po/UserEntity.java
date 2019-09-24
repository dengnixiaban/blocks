package cn.blocks.userservice.repository.po;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @description
 *          mongo test
 *
 * @auther Somnus丶y
 * @date 2019/9/24 12:01
 */
@Data
@ToString
@Document("users")
public class UserEntity {

    @Id
    private String id;

    @Field("name")
    private String name;

}
