package cn.blocks.httpclient.interceptor;

import feign.RequestTemplate;

/**
 * @description
 *          feign client Interceptor
 *
 * @author Somnus丶y
 * @date 2019/9/9 11:43
 */
public interface FeignClientInterceptor {


    /**
     * @description
     *          执行内容
     *
     * @param template
     *          请求体
     *
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/9/9
     */
    void apply(RequestTemplate template);




    /**
     * @description
     *      匹配
     *
     * @return boolean
     * @throws
     * @author Somnus丶y
     * @date 2019/9/9
     */
    boolean match(RequestTemplate template);

}
