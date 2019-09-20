package cn.blocks.userapi.model;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description
 *          用户dto
 *
 * @author Somnus丶y
 * @date 2019/8/30 17:05
 */
@ApiModel("用户dto实体")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDTO extends BaseTimeDTO implements Serializable {

    private static final long serialVersionUID = -1716225104035236394L;
    /**
     * 用户唯一key
     */
    private Long id;

    /**
     * 用户账户
     */
    @NotNull
    private String account;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户描述
     */
    private String desc;

    /**
     * 用户性别 1:女  -1男
     */
    private Integer gender;

    /**
     * 用户头像
     */
    private String icon;

    /**
     * 用户密码
     */
    private String password;



}
