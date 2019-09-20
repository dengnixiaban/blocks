package cn.blocks.userservice.repository.po;

import cn.blocks.commonmysql.model.po.BaseTimePO;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

/**
 * @description
 *          用户PO
 *
 * @author Somnus丶y
 * @date 2019/9/10 18:48
 */
@TableName("t_users")
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserPO extends BaseTimePO {

    /**
     * 用户唯一key
     */
    @TableField("id")
    private Long id;

    /**
     * 用户账户
     */
    @TableField("account")
    private String account;

    /**
     * 用户昵称
     */
    @TableField("nickName")
    private String nickName;

    /**
     * 用户描述
     */
    @TableField("desc")
    private String desc;

    /**
     * 用户性别 1:女  -1男
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 用户头像
     */
    @TableField("icon")
    private String icon;

    /**
     * 用户密码
     */
    @TableField("password")
    private String password;


}
