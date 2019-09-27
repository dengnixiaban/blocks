package cn.blocks.commoncache.model;

import cn.blocks.commoncache.constant.CacheType;
import cn.blocks.commoncache.loader.DataLoader;
import cn.blocks.commonutils.utils.LogUtils;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @description
 *          缓存主体
 *
 * @author Somnus丶y
 * @date 2019/9/27 11:02
 */
@Data
@Slf4j
public class BlocksCache {

    /**
     * 缓存名称
     */
    private String cacheName;

    /**
     * 缓存类型
     */
    private CacheType cacheType;

    /**
     * 数据加载器
     */
    private DataLoader<String,Object> dataLoader;

    /**
     * guava
     */
    private LoadingCache<String, Object> loadingCache;

    public BlocksCache(){
        loadingCache = CacheBuilder.newBuilder().build(new CacheLoader<String, Object>() {
            @Override
            public Object load(String key) throws Exception {
                return dataLoader.getData(key);
            }
        });
    }



    public Object get(String key){
        Object resp = null;
        try {
            resp = loadingCache.get(key);
        } catch (ExecutionException e) {
            LogUtils.error(log,e,"缓存加载失败,name:%s,key:%s",cacheName,key);
        }
        return resp;
    }



}
