package cn.blocks.commonutils.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @description
 *          异常枚举
 *   {@code 异常码}
 *
 * @author Somnus丶y
 * @date 2019/8/31 17:40
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {

    /*********************************************7*******************************************/
    IDSEQERROR("700001","mongo序列id获取异常"),


    /*******************************************8**********************************************/
    CACHETYPEERROR("800001","缓存类型不匹配:%s"),


    /*********************************************9*******************************************/

    HTTPSERVERFRAMERROR("900001","httpserver框架异常:%s"),

    CACHEFRAMEWORKERROR("910001","缓存框架异常:%s");




    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
