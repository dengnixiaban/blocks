package cn.blocks.scheduleservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/9/28 11:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MqEvent {

    private String name;

    private String code;

}
