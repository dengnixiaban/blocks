package cn.blocks.userapi.model;

import lombok.*;

/**
 * @description
 *          平台对象
 *
 * @auther Somnus丶y
 * @date 2019/10/14 16:38
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PlatformDTO extends BaseTimeDTO{

    /**
     * 平台id
     */
    private Long id;

    /**
     * 平台描述
     */
    private String desc;


}
