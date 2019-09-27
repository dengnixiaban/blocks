package cn.blocks.commoncache.constant;

import cn.blocks.commoncache.core.BlocksCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description
 *          缓存全局环境
 *
 * @author Somnus丶y
 * @date 2019/9/27 9:59
 */
public class CacheEnv {

    /**
     * 启用了哪些缓存
     */
    public static final Map<String,Boolean> enbaleCaches = new HashMap<>();

    /**
     * guava缓存名称
     */
    public static final String guavaCacheName = "guava";

    /**
     * redis缓存名称
     */
    public static final String redisCacheName = "redis";

    /**
     * caffeine缓存名称
     */
    public static final String caffeineCacheName = "caffeine";


    /**
     * guava缓存池
     */
    public static final ConcurrentHashMap<String, BlocksCache> guavaCachePool = new ConcurrentHashMap<>();

    /**
     * caffeine缓存池
     */
    public static final ConcurrentHashMap<String, BlocksCache> caffeineCachePool = new ConcurrentHashMap<>();

    /**
     * redis
     */
    public static final ConcurrentHashMap<String, BlocksCache> redisCachePool = new ConcurrentHashMap<>();

}
