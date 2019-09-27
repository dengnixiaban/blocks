package cn.blocks.userservice.cache;

import cn.blocks.commoncache.annotation.RedisCache;
import cn.blocks.commoncache.core.AbstractDataLoader;
import org.springframework.stereotype.Component;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/9/27 11:27
 */
@Component("redisCacheService")
public class RedisCacheServiceImpl extends AbstractDataLoader {


    @Override
    @RedisCache(name = "redisCache",redisExpire = 60)
    public Object getData(Object key) {
        return "redis:"+key;
    }

}
