package cn.blocks.commonutils.exception;

import lombok.Data;

/**
 * @description
 *      业务异常
 *
 * @author Somnus丶y
 * @date 2019/9/12 12:27
 */
@Data
public class BusinessException extends RuntimeException{

    /**
     * code
     */
    private String code;

    /**
     * 信息
     */
    private String msg;

    public BusinessException(ExceptionEnum em) {
        super();
        this.code = em.getCode();
        this.msg = em.getMsg();
    }


}
