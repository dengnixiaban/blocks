package cn.blocks.commonamqp.constant;

/**
 * @description
 *          mq 常量 for blocks
 *
 * @auther Somnus丶y
 * @date 2019/10/18 17:23
 */
public class AmqpConstant {

    //exchange

    public static final String TEST_TOPIC = "topic.test1";

    //dead

    public static final String DEAD_LETTER_EXCHANGE = "TDL_EXCHANGE";

    public static final String DEAD_LETTER_TEST_ROUTING_KEY = "TDL_KEY";

    public static final String DEAD_LETTER_REDIRECT_ROUTING_KEY = "TKEY_R";

    //queue

    public static final String QUEUE_TEST = "queue.test1";

    public static final String DEAD_LETTER_QUEUE = "TDL_QUEUE";

    public static final String REDIRECT_QUEUE = "TREDIRECT_QUEUE";

}
