package cn.blocks.commoncache.model;

import cn.blocks.commoncache.loader.DataLoader;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * @description
 *          缓存初始化对象event
 * @author Somnus丶y
 * @date 2019/9/27 10:45
 */
@Data
public class CacheClassEvent extends ApplicationEvent {

    private DataLoader dataLoader;


    public CacheClassEvent(Object source) {
        super(source);
    }


}
