package cn.blocks.httpclient.config;

import cn.blocks.httpclient.interceptor.FeignClientInterceptor;
import lombok.Data;
import org.springframework.core.annotation.Order;
import org.springframework.core.style.ToStringCreator;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Map;

/**
 * @description
 *          AnnotatedElement for order
 *       {@link org.springframework.core.annotation.Order}
 *
 * @auther Somnus丶y
 * @date 2019/9/9 12:21
 */
@Data
public class OrderedAnnotatedElement implements AnnotatedElement {

    public final String name;

    private Order order = null;

    private Integer value;

    private FeignClientInterceptor interceptor;

    public OrderedAnnotatedElement(MetadataReaderFactory metadataReaderFactory, String name)
            throws IOException {
        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(name);
        AnnotationMetadata metadata = metadataReader.getAnnotationMetadata();
        Map<String, Object> attributes = metadata
                .getAnnotationAttributes(Order.class.getName());
        this.name = name;
        if (attributes != null && attributes.containsKey("value")) {
            this.value = (Integer) attributes.get("value");
            this.order = new Order() {
                @Override
                public Class<? extends Annotation> annotationType() {
                    return Order.class;
                }

                @Override
                public int value() {
                    return OrderedAnnotatedElement.this.value;
                }
            };
        }
    }

    public OrderedAnnotatedElement(MetadataReaderFactory metadataReaderFactory, String name,
                                   FeignClientInterceptor obj)
            throws IOException {
        this(metadataReaderFactory,name);
        this.interceptor = obj;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        if (annotationClass == Order.class) {
            return (T) this.order;
        }
        return null;
    }

    @Override
    public Annotation[] getAnnotations() {
        return this.order == null ? new Annotation[0]
                : new Annotation[] { this.order };
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return getAnnotations();
    }

    @Override
    public String toString() {
        return new ToStringCreator(this).append("name", this.name)
                .append("value", this.value).toString();
    }


}
