package cn.blocks.httpserver.controllerAdvice;

import cn.blocks.commonutils.model.BaseResp;
import cn.blocks.httpserver.annotation.ExcludAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Executable;
import java.util.Objects;

/**
 * @description
 *          响应对象封装
 *
 * @auther Somnus丶y
 * @date 2019/9/11 17:38
 */
@Slf4j
@ControllerAdvice
public class RespDataAdvice implements ResponseBodyAdvice {

    /**
     * support
     *
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        Executable executable = returnType.getExecutable();
        if(Objects.isNull(executable)){
            return false;
        }
        ExcludAdvice annotation = executable.getAnnotation(ExcludAdvice.class);
        if(Objects.nonNull(annotation)){
            return false;
        }
        return true;
    }

    /**
     * 响应body
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType,
                                            ServerHttpRequest request, ServerHttpResponse response) {
        //静态资源过滤
        String path = request.getURI().getPath();
        if(path.contains(".css")|| path.contains(".js") || path.contains(".png")
           || path.contains(".jpg") || path.contains(".jpeg")|| path.contains(".html")
           ||path.contains("/swagger") || path.contains("/v2/swagger"))
        {
            return body;
        }
        //不需要封装
        if(body instanceof BaseResp){
            return body;
        }
        BaseResp resp = new BaseResp();
        resp.setData(body);
        return resp;
    }
}
