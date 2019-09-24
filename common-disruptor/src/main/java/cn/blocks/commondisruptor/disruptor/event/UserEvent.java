package cn.blocks.commondisruptor.disruptor.event;

import lombok.Data;
import lombok.ToString;

/**
 * @description
 *          userEvent
 *
 * @author Somnus丶y
 * @date 2019/9/24 17:41
 */
@Data
@ToString
public class UserEvent {

    private String id;

    private String name;

    private boolean converted;

}
