package cn.blocks.commoncache.loader;

/**
 * @description
 *          真实数据加载
 *
 * @author Somnus丶y
 * @date 2019/9/26 10:26
 */
@FunctionalInterface
public interface DataLoader<K,V> {

    /**
     * @description
     *          真实数据加载
     *
     * @param key
     * @return V
     * @throws
     * @author Somnus丶y
     * @date 2019/9/26
     */
    V getData(K key);


}
