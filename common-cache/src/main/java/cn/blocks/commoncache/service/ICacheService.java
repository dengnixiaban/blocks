package cn.blocks.commoncache.service;

import cn.blocks.commoncache.loader.DataLoader;

/**
 * @description
 *         缓存接口
 *
 * @author Somnus丶y
 * @date 2019/9/26 16:53
 */
public interface ICacheService<K,V> extends DataLoader<K,V> {

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

}
