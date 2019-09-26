package cn.blocks.userservice.cache;

import cn.blocks.commoncache.builder.BlocksCacheBuilder;
import cn.blocks.commoncache.loader.MemoryCacheLoader;
import cn.blocks.commoncache.service.ICacheService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @description
 * @author Somnus丶y
 * @date 2019/9/26 16:56
 */
@Component("testCacheService")
public class TestCacheServiceImpl implements ICacheService<String,String> {

    private MemoryCacheLoader<String,String> loader;

    @PostConstruct
    public void init(){
        BlocksCacheBuilder builder = new BlocksCacheBuilder<String,String>();
        this.loader = builder.guavaCacheLoader().build(this);
    }


    @Override
    public String getData(String key) {
        return key+"111";
    }


    @Override
    public String load(String key) {
        return loader.load(key);
    }

}
