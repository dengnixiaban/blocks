package cn.blocks.commoncache.registrar;

import cn.blocks.commoncache.annotation.GuavaCache;
import cn.blocks.commoncache.constant.CacheEnv;
import cn.blocks.commoncache.loader.DataLoader;
import cn.blocks.commoncache.model.BlocksCache;
import cn.blocks.commoncache.model.CacheClassEvent;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @description
 *          CacheClassEvent监听
 *
 *          主要动作--构造缓存对象
 *
 * @auther Somnus丶y
 * @date 2019/9/27 10:41
 */
public class BlocksCacheApplicationListener implements ApplicationListener<CacheClassEvent> {

    @Override
    public void onApplicationEvent(CacheClassEvent event) {
        DataLoader dataLoader = event.getDataLoader();
        Method[] methods = dataLoader.getClass().getMethods();
        Method method = null;
        for (Method m :methods) {
            if(m.getName().equals("getData")){
                method = m;
                break;
            }
        }
        //判断是哪种缓存  todo 暂时guava
        GuavaCache annotation = method.getAnnotation(GuavaCache.class);
        if(Objects.nonNull(annotation)&&annotation.enable()){
            BlocksCache blocksCache = new BlocksCache();
            blocksCache.setCacheName(annotation.name());
            blocksCache.setDataLoader(dataLoader);
            CacheEnv.cachePool.put(annotation.name(),blocksCache);
        }

    }

}
