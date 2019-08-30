package cn.blocks.userapi.model;

import lombok.*;

/**
 * @description
 *          用户dto
 *
 * @auther Somnus丶y
 * @date 2019/8/30 17:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDTO {

    private String id;

    private String name;

}
