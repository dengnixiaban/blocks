package cn.blocks.httpserver.registrar;

import cn.blocks.httpserver.annotation.EnableHttpServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.CollectionUtils;
import static cn.blocks.httpserver.constant.HttpServerConstant.*;

import java.util.List;
import java.util.Map;

/**
 * @description
 *          http-server模块 配置注入器
 *
 * @auther Somnus丶y
 * @date 2019/8/31 15:08
 */
@Slf4j
public class HttpServerRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(
            AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        registerDefaultConfiguration(metadata, registry);
    }

    private void registerDefaultConfiguration(AnnotationMetadata metadata, BeanDefinitionRegistry registry){
        Map<String, Object> annotationAttributes = metadata.
                getAnnotationAttributes(EnableHttpServer.class.getName(), true);
        if(!CollectionUtils.isEmpty(annotationAttributes)
           &&annotationAttributes.containsKey(AUTOIMPORTCONFIGUTATIONKEY)){
            String[] classes = (String[])annotationAttributes.get(AUTOIMPORTCONFIGUTATIONKEY);
            if(classes.length>0){
                for (String cls :classes) {
                    try {
                        registerClientConfiguration(registry,"blocks",Class.forName(cls));
                    } catch (ClassNotFoundException e) {
                        log.info("class未找到");
                    }
                }
            }
        }else{
            log.info("httpserver没有注入任何配置");
        }
    }



    /**
     * @description
     *          注册
     *
     * @param registry, name, configuration
     * @return void
     * @throws
     * @author Somnus丶y
     * @date 2019/8/31
     */
    private void registerClientConfiguration(BeanDefinitionRegistry registry, Object name,
                                             Class configuration) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder
                .genericBeanDefinition(configuration);
        /*builder.addConstructorArgValue(name);
        builder.addConstructorArgValue(configuration);*/
        registry.registerBeanDefinition(
                name + "." + configuration.getSimpleName(),
                builder.getBeanDefinition());
    }


}
