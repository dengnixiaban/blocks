package cn.blocks.commoncache.loader;

import cn.blocks.commoncache.constant.CacheType;

/**
 * @description
 *          分布式缓存加载方式
 *
 * @author Somnus丶y
 * @date 2019/9/26 9:55
 */
public interface DistributedCacheLoader<K,V> {


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
     *          刷新缓存
     *
     * @param key
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/26
     */
    void refresh(K key);



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


}
