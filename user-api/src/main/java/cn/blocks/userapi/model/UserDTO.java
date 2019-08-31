package cn.blocks.userapi.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date appointDate; //预定的预成班日期

}
