package cn.blocks.commoncache.loader;

import cn.blocks.commoncache.constant.CacheType;

/**
 * @description
 *         jvm 缓存loader
 *
 * @author Somnus丶y
 * @date 2019/9/26 9:59
 */
public interface MemoryCacheLoader<K,V> {


    /**
     * @description
     *          缓存加载
     *
     * @param key
     * @return V
     * @throws
     * @author Somnus丶y
     * @date 2019/9/26
     */
    V load(K key);



    /**
     * @description
     *          主动设置缓存
     *
     * @param key, value
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/26
     */
    void putCahce(K key,V value);



    /**
     * @description
     *          刷新本机
     *
     * @param key
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/26
     */
    void refreshSlef(K key);



    /**
     * @description
     *          广播刷新
     *
     * @param key
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/26
     */
    void refreshPublic(K key);



    /**
     * @description
     *          删除缓存
     *
     * @param key
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/26
     */
    void removeCache(K key);




    /**
     * @description
     *          设置分布式loader
     *
     * @param distributedCacheLoader
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/26
     */
    void distributedCacheLoader(DistributedCacheLoader<K,V> distributedCacheLoader);



    /**
     * @description
     *          获取type
     *
     * @return cn.blocks.commoncache.constant.CacheType
     * @throws
     * @author Somnus丶y
     * @date 2019/9/26
     */
    CacheType getType();



    /**
     * @description
     *          设置type
     *
     * @param type
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/26
     */
    void type(CacheType type);



    /**
     * @description
     *          数据加载
     *
     * @param loader
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/26
     */
    <K1 extends K, V1 extends V> MemoryCacheLoader<K,V> build(DataLoader<K1,V1> loader);

}
