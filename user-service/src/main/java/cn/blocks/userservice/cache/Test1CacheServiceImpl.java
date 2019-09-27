package cn.blocks.userservice.cache;

import cn.blocks.commoncache.annotation.GuavaCache;
import cn.blocks.commoncache.loader.impl.AbstractDataLoader;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/9/27 11:27
 */
@Component("test1CacheService")
//@DependsOn("blocksCacheApplicationListener")
public class Test1CacheServiceImpl extends AbstractDataLoader {


    @Override
    @GuavaCache(name = "test1")
    public Object getData(Object key) {
        return "val:"+key;
    }

}
