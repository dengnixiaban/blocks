package cn.blocks.commonutils.exception;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/8/31 16:24
 */
public class BlocksFrameWorkException extends RuntimeException{

    private String code;

    public BlocksFrameWorkException(ExceptionEnum em,Exception e) {
        super(String.format(em.getMsg(),e.getMessage()),e.getCause());
        this.code = em.getCode();
    }

    public BlocksFrameWorkException() {
    }

    public BlocksFrameWorkException(String code,String message) {
        super(message);
        this.code = code;
    }

    public BlocksFrameWorkException(String code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BlocksFrameWorkException(Throwable cause) {
        super(cause);
        this.code = code;
    }

    public BlocksFrameWorkException(String code,String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

}
