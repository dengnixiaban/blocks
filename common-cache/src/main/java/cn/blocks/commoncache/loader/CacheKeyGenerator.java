package cn.blocks.commoncache.loader;

/**
 * @description
 *          缓存key生成器
 *
 * @author Somnus丶y
 * @date 2019/9/27 14:14
 */
public interface CacheKeyGenerator {


    String generate(Object... orgs);


}
