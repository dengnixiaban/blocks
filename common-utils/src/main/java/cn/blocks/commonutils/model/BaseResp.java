package cn.blocks.commonutils.model;

import lombok.Data;

import static cn.blocks.commonutils.model.HttpEnum.SUCCESS;

/**
 * @description
 *          统一响应对象
 *
 * @author Somnus丶y
 * @date 2019/9/11 17:17
 */
@Data
public class BaseResp<T> {

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应data
     */
    private T data;

    public BaseResp() {
        this.code = SUCCESS.getCode();
        this.msg = SUCCESS.getMsg();
    }

    public BaseResp(HttpEnum httpEnum) {
        this.code = httpEnum.getCode();
        this.msg = httpEnum.getMsg();
    }


}
