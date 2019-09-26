package cn.blocks.commoncache.builder;

import cn.blocks.commoncache.constant.CacheType;
import cn.blocks.commoncache.loader.DistributedCacheLoader;
import cn.blocks.commoncache.loader.MemoryCacheLoader;
import cn.blocks.commoncache.loader.impl.GuavaCacheLoader;

/**
 * @description
 *          缓存builder
 *
 * @author Somnus丶y
 * @date 2019/9/26 10:16
 */
public class BlocksCacheBuilder<K,V> {

    /**
     * 参数
     */
    private String spec;




    public DistributedCacheLoader<K,V> redisCacheLoader(){
        return null;
    }

    /**
     * @description
     *          guavaloader
     *
     * @return cn.blocks.commoncache.loader.MemoryCacheLoader<K,V>
     * @throws
     * @author Somnus丶y
     * @date 2019/9/26
     */
    public MemoryCacheLoader<K,V> guavaCacheLoader(){
        return new GuavaCacheLoader<>();
    }




    public MemoryCacheLoader<K,V> guavaCacheLoader(CacheType type){
        return new GuavaCacheLoader<>(type);
    }




    /**
     * @description
     *          weak  key
     *
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/26
     */
    public void weakKeys(){

    }



    /**
     * @description
     *          weak value
     *
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/26
     */
    public void weakValues(){

    }




    /**
     * @description
     *          soft value
     *
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/26
     */
    public void softValues(){

    }



    public BlocksCacheBuilder<K,V> spec(String spec){
        this.spec = spec;
        return this;
    }


}
