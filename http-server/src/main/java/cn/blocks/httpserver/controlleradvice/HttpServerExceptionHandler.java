package cn.blocks.httpserver.controlleradvice;

import cn.blocks.commonutils.exception.BusinessException;
import cn.blocks.commonutils.model.BaseResp;
import cn.blocks.commonutils.model.HttpEnum;
import cn.blocks.commonutils.utils.LogUtils;
import cn.blocks.httpserver.annotation.ExcludAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description
 *          http server 异常抓取
 *
 * @auther Somnus丶y
 * @date 2019/9/11 17:14
 */
@Slf4j
@ControllerAdvice
public class HttpServerExceptionHandler {



    /**
     * @description
     *          业务异常抓取
     *
     * @param e
     * @return cn.blocks.commonutils.model.BaseResp
     * @throws
     * @author Somnus丶y
     * @date 2019/9/12
     */
    @ExcludAdvice
    @ResponseBody
    @ExceptionHandler({ BusinessException.class})
    public BaseResp businessException(BusinessException e){
        LogUtils.error(log,e,"ExceptionHandler获取MethodArgumentNotValidException异常");
        BaseResp resp = new BaseResp();
        resp.setCode(e.getCode());
        resp.setMsg(e.getMsg());
        return resp;
    }



    /**
     * @description
     *          参数异常抓取
     *
     * @param e
     * @return cn.blocks.commonutils.model.BaseResp
     * @throws
     * @author Somnus丶y
     * @date 2019/9/11
     */
    @ExcludAdvice
    @ResponseBody
    @ExceptionHandler({ MethodArgumentNotValidException.class})
    public BaseResp methodArgumentNotValidException(MethodArgumentNotValidException e){
        LogUtils.error(log,e,"ExceptionHandler获取MethodArgumentNotValidException异常");
        BaseResp resp = new BaseResp(HttpEnum.PARAMTERERROR);
        String field = e.getBindingResult().getFieldError().getField();
        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        resp.setData(field+":"+defaultMessage);
        return resp;
    }




    /**
     * @description
     *          漏掉的异常
     *
     * @param request, response, e
     * @return cn.blocks.commonutils.model.BaseResp
     * @throws
     * @author Somnus丶y
     * @date 2019/9/11
     */
    @ExcludAdvice
    @ResponseBody
    @ExceptionHandler({ Exception.class})
    public BaseResp exception(HttpServletRequest request, HttpServletResponse response,
                              Exception e){
        LogUtils.error(log,e,"ExceptionHandler获取Exception异常");
        BaseResp resp = new BaseResp(HttpEnum.UNKNOWERROR);
        resp.setData(e.getMessage());
        return resp;
    }


}
