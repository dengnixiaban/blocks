/**
 * @description
 *
 * @author Somnus丶y
 * @date 2019/9/20 19:08
 */
package cn.blocks.commoncache;


/**
 *
 * 缓存结构
 *
 * 第一层：抽象-分布式缓存结构
 * 实例1：redis
 *
 * 第二层：抽象-jvm单机内存缓存
 * 实例1：guava
 * 实例2：caffeine
 *
 *
 * 主要功能：异步加载、通知刷新、定时失效、触发清除、实例装配式拆卸、日志记录、命中统计、lru
 *
 *
 *
 * 两套使用方案：
 * ①,使用blocks框架封装,@EnableBlocksCache
 * 第一种好处在于可以使用封装的一些统计、日志等可由开发者定制化的功能
 *
 * ②,使用springboot嵌入式缓存caffeine
 * 第二种spring提供强大稳定的功能支撑。。。总之牛逼
 *
 */