package cn.blocks.httpclient.interceptor;

import cn.blocks.commonutils.utils.LogUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * @description
 *          处理feign  get调用  javabean
 *
 * @author Somnus丶y
 * @date 2019/9/9 11:46
 */
@Order(value = 1)
@Slf4j
public class FeignGetBeanInterceptor implements FeignClientInterceptor{

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void apply(RequestTemplate template) {
        if (HttpMethod.GET.name().equals(template.method())
            && null != template.body()) {
            try {
                JsonNode jsonNode = objectMapper.readTree(template.body());
                template.body("");

                Map<String, Collection<String>> queries = new HashMap<>();
                buildQuery(jsonNode, "", queries);
                template.queries(queries);
            } catch (IOException e) {
                LogUtils.error(log,e,"【拦截GET请求POJO方式】-出错了：{%s}", ExceptionUtils.getStackFrames(e));
                throw new RuntimeException();
            }
        }
    }

    @Override
    public boolean match(RequestTemplate template) {
        if (HttpMethod.GET.name().equals(template.method())){
            return true;
        }else{
            return false;
        }
    }



    private void buildQuery(JsonNode jsonNode, String path, Map<String, Collection<String>> queries) {
        // 叶子节点
        if (!jsonNode.isContainerNode()) {
            if (jsonNode.isNull()) {
                return;
            }
            Collection<String> values = queries.get(path);
            if (null == values) {
                values = new ArrayList<>();
                queries.put(path, values);
            }
            values.add(jsonNode.asText());
            return;
        }
        // 数组节点
        if (jsonNode.isArray()) {
            Iterator<JsonNode> it = jsonNode.elements();
            while (it.hasNext()) {
                buildQuery(it.next(), path, queries);
            }
        } else {
            Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                if (StringUtils.hasText(path)) {
                    buildQuery(entry.getValue(), path + "." + entry.getKey(), queries);
                } else {  // 根节点
                    buildQuery(entry.getValue(), entry.getKey(), queries);
                }
            }
        }
    }


}
