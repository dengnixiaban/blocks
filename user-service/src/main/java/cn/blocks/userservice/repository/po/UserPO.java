package cn.blocks.userservice.repository.po;

import cn.blocks.commonmysql.model.po.BaseTimePO;
import lombok.*;

/**
 * @description
 *          用户PO
 *
 * @auther Somnus丶y
 * @date 2019/9/10 18:48
 */
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserPO extends BaseTimePO {

    /**
     * 用户唯一key
     */
    private Long id;

    /**
     * 用户账户
     */
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
