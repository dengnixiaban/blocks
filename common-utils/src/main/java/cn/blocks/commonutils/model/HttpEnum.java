package cn.blocks.commonutils.model;

/**
 * @description
 *          http码 字典
 *
 * @auther Somnus丶y
 * @date 2019/9/11 17:22
 */
public enum  HttpEnum {

    SUCCESS("000000","成功"),
    UNKNOWERROR("099999","服务器异常"),
    PARAMTERERROR("011111","参数异常");

    /**
     * code
     */
    private String code;

    /**
     * msg
     */
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

    HttpEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
