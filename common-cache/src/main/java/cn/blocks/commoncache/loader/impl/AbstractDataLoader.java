package cn.blocks.commoncache.loader.impl;

import cn.blocks.commoncache.annotation.GuavaCache;
import cn.blocks.commoncache.constant.CacheEnv;
import cn.blocks.commoncache.loader.DataLoader;
import cn.blocks.commoncache.model.BlocksCache;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @description
 *      抽象数据加载器
 *
 * @author Somnus丶y
 * @date 2019/9/27 10:49
 */
public abstract class AbstractDataLoader implements ApplicationContextAware, DataLoader {


    /**
     * @description
     *          事件发布
     *
     * @param applicationContext
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/27
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Method[] methods = this.getClass().getMethods();
        Method method = null;
        for (Method m :methods) {
            if(m.getName().equals("getData")){
                method = m;
                break;
            }
        }
        //判断是哪种缓存  todo 暂时guava
        GuavaCache annotation = method.getAnnotation(GuavaCache.class);
        if(Objects.nonNull(annotation) && annotation.enable()){
            BlocksCache blocksCache = new BlocksCache();
            blocksCache.setCacheName(annotation.name());
            blocksCache.setDataLoader(this);
            CacheEnv.cachePool.put(annotation.name(),blocksCache);
        }
    }

}
