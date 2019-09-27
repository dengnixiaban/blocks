package cn.blocks.commoncache.config;

/**
 * @description
 *          缓存redis工具包
 *
 * @author Somnus丶y
 * @date 2019/9/27 15:13
 */
public class CacheRedisUtils {

    private static final String cachePrefix = "cache:%s";


    public static String getRedisCacheKey(String key){
        return String.format(cachePrefix,key);
    }

}
