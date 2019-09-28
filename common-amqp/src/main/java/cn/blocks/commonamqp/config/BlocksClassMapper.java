package cn.blocks.commonamqp.config;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.ClassMapper;

/**
 * @description
 *            class mapper
 *
 *        todo   暂时不需要
 *
 * @author Somnus丶y
 * @date 2019/9/28 14:15
 */
public class BlocksClassMapper implements ClassMapper {


    @Override
    public void fromClass(Class<?> clazz, MessageProperties properties) {

    }

    @Override
    public Class<?> toClass(MessageProperties properties) {
        return null;
    }

}
