package cn.blocks.commonutils.utils;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @description
 *          环境变量
 *
 *
 * @auther Somnus丶y
 * @date 2019/11/6 17:57
 */
public class EnvironmentUtils implements EnvironmentAware {

    private static Environment environment;


    @Override
    public void setEnvironment(Environment environment) {
        EnvironmentUtils.environment = environment;
    }


    /**
     * @description
     *          获取String类型环境变量
     *
     * @param key
     * @return java.lang.String
     * @throws
     * @author Somnus丶y
     * @date 2019/11/6
     */
    public static String getStrEnv(String key){
        return environment.getProperty(key);
    }


}
