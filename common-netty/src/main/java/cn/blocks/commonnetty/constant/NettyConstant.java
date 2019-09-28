package cn.blocks.commonnetty.constant;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/9/28 15:16
 */
public class NettyConstant {

    /**
     * 最大线程量
     */
    public static final int MAX_THREADS = 1024;
    /**
     * 数据包最大长度
     */
    public static final int MAX_FRAME_LENGTH = 65535;

    //读超时时间
    public static final  int SERVER_READ_IDEL_TIME_OUT = 70;

    //写超时时间
    public static final int SERVER_WRITE_IDEL_TIME_OUT = 60;

    //所有类型的超时时间
    public static final int SERVER_ALL_IDEL_TIME_OUT = 130;



}
