package cn.blocks.commonutils.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @description
 *          异常枚举
 *   {@code 异常码}
 *
 * @auther Somnus丶y
 * @date 2019/8/31 17:40
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {
    HTTPSERVERFRAMERROR("","");

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
