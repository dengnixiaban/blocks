package cn.blocks.commoncache.loader.impl;

import cn.blocks.commoncache.constant.CacheType;
import cn.blocks.commoncache.loader.DataLoader;
import cn.blocks.commoncache.loader.DistributedCacheLoader;
import cn.blocks.commoncache.loader.MemoryCacheLoader;
import cn.blocks.commonutils.utils.LogUtils;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @description
 *
 *
 * @author Somnus丶y
 * @date 2019/9/26 12:02
 */
@Data
@Slf4j
public class GuavaCacheLoader<K,V> implements MemoryCacheLoader<K,V> {

    /**
     * 分布式loader
     */
    private DistributedCacheLoader<K,V> distributedCacheLoader;

    /**
     * 数据拉取
     */
    private DataLoader dataLoader;

    /**
     * 类型  默认仅guava
     */
    private CacheType type = CacheType.GUAVA;

    /**
     * guava cachebuilder
     */
    private CacheBuilder cacheBuilder;

    /**
     * guava 缓存对象
     */
    private LoadingCache<K, V> cache;


    public GuavaCacheLoader(){
        this.cacheBuilder = CacheBuilder.newBuilder();
    }


    public GuavaCacheLoader(CacheType type){
        this.type = type;
        this.cacheBuilder = CacheBuilder.newBuilder();
    }


    @Override
    public V load(K key) {
        try {
            return cache.get(key);
        } catch (ExecutionException e) {
            LogUtils.error(log,e,"guavaCacheLoader异常,key%s",key);
            return null;
        }
    }


    @Override
    public void putCahce(K key, V value) {

    }


    @Override
    public void refreshSlef(Object key) {

    }

    @Override
    public void refreshPublic(K key) {

    }

    @Override
    public void removeCache(K key) {

    }

    @Override
    public void distributedCacheLoader(DistributedCacheLoader distributedCacheLoader) {

    }

    @Override
    public CacheType getType() {
        return null;
    }

    @Override
    public void type(CacheType type) {

    }

    @Override
    public <K1 extends K, V1 extends V> MemoryCacheLoader<K,V> build(DataLoader<K1,V1> loader){
        this.cache = cacheBuilder.build(new CacheLoader<K1, V>() {
            @Override
            public V load(K1 key) throws Exception {
                if(CacheType.GUAVA.equals(type)){
                    return loader.getData(key);
                }else{
                    return distributedCacheLoader.load(key);
                }
            }
        });
        return this;
    }


}
