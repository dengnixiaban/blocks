package cn.blocks.commoncache.loader.impl;

import cn.blocks.commoncache.loader.CacheKeyGenerator;

/**
 * @description
 *          默认key生成器
 *
 * @author Somnus丶y
 * @date 2019/9/27 14:16
 */
public class DefaultCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public String generate(Object... orgs) {
        return orgs[0].toString();
    }

}
