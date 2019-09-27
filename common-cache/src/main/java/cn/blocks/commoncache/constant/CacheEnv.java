package cn.blocks.commoncache.constant;

import cn.blocks.commoncache.model.BlocksCache;

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
     * 缓存池
     */
    public static final ConcurrentHashMap<String, BlocksCache> cachePool = new ConcurrentHashMap<>();

}
