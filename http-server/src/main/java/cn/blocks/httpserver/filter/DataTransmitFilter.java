package cn.blocks.httpserver.filter;

import cn.blocks.commonutils.utils.LogUtils;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description
 *          进程数据传递支持
 *
 * @author Somnus丶y
 * @date 2019/9/12 16:23
 */
@WebFilter(filterName = "dataTransmitFilter",urlPatterns = "/*",asyncSupported=true)
public class DataTransmitFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String logId = httpServletRequest.getHeader(LogUtils.LOGID);
        if(StringUtils.isEmpty(logId)){
            //无日志id传递----初始化
            LogUtils.initLogId();
        }else{
            //传递
            LogUtils.putLogId(logId);
        }
        chain.doFilter(request,response);
    }


    @Override
    public void destroy() {

    }

}
