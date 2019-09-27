package cn.blocks.userservice.cache;

import cn.blocks.commoncache.annotation.GuavaCache;
import cn.blocks.commoncache.core.AbstractDataLoader;
import org.springframework.stereotype.Component;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/9/27 11:27
 */
@Component("guavaCacheService")
public class GuavaCacheServiceImpl extends AbstractDataLoader {


    @Override
    @GuavaCache(name = "guavaCache",useRedis = true,redisExpire = 60)
    public Object getData(Object key) {
        return "guava:"+key;
    }

}
