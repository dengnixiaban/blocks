package cn.blocks.commonutils.exception;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/8/31 16:24
 */
public class BlocksFrameWorkException extends RuntimeException{


    public BlocksFrameWorkException() {
    }

    public BlocksFrameWorkException(String message) {
        super(message);
    }

    public BlocksFrameWorkException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlocksFrameWorkException(Throwable cause) {
        super(cause);
    }

    public BlocksFrameWorkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
