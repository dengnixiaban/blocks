package cn.blocks.userservice.cache;

import cn.blocks.commoncache.annotation.CaffeineCache;
import cn.blocks.commoncache.annotation.GuavaCache;
import cn.blocks.commoncache.core.AbstractDataLoader;
import org.springframework.stereotype.Component;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/9/27 11:27
 */
@Component("caffeineCacheService")
public class CaffeineCacheServiceImpl extends AbstractDataLoader {


    @Override
    @CaffeineCache(name = "caffeineCache", useRedis = true, redisExpire = 60)
    public Object getData(Object key) {
        return "caffeine:"+key;
    }

}
