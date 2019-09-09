package cn.blocks.httpclient.registrar;

import cn.blocks.httpclient.config.HttpClientBootstrapConfiguration;
import cn.blocks.httpclient.config.OrderedAnnotatedElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description
 *          httpclient 自动配置
 *
 * @auther Somnus丶y
 * @date 2019/9/6 9:41
 */
@Slf4j
public class HttpClientImportSelector implements DeferredImportSelector {


    private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();


    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        List<String> names = new ArrayList<>(SpringFactoriesLoader
                .loadFactoryNames(HttpClientBootstrapConfiguration.class, classLoader));
        List<OrderedAnnotatedElement> elements = new ArrayList<>();
        for (String name : names) {
            try {
                elements.add(
                        new OrderedAnnotatedElement(this.metadataReaderFactory, name));
            }
            catch (IOException e) {
                continue;
            }
        }
        AnnotationAwareOrderComparator.sort(elements);

        String[] classNames = elements.stream().map(e -> e.name).toArray(String[]::new);

        return classNames;
    }


}
