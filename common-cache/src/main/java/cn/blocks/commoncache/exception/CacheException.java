package cn.blocks.commoncache.exception;

import cn.blocks.commonutils.exception.ExceptionEnum;
import lombok.Data;

/**
 * @description
 *          缓存类型异常
 *
 * @author Somnus丶y
 * @date 2019/9/26 10:47
 */
@Data
public class CacheException extends RuntimeException{

    /**
     * code
     */
    private String code;

    /**
     * 信息
     */
    private String msg;

    public CacheException(ExceptionEnum em,String desc) {
        super();
        this.code = em.getCode();
        this.msg = String.format(em.getMsg(),desc);
    }

    public CacheException(ExceptionEnum em,Exception e) {
        super(String.format(em.getMsg(),e.getMessage()),e.getCause());
        this.code = em.getCode();
    }


}
