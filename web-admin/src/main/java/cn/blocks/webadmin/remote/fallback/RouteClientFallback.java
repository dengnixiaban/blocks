package cn.blocks.webadmin.remote.fallback;

import cn.blocks.commonutils.utils.LogUtils;
import cn.blocks.userapi.model.UserDTO;
import cn.blocks.webadmin.remote.reactive.RouteClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.stream.Stream;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/10/15 10:43
 */
@Slf4j
@Component
public class RouteClientFallback implements RouteClient {

    @Override
    public Flux<UserDTO> list() {
        LogUtils.info(log,"user-service fallback");
        return Flux.fromStream(Stream.of(new UserDTO()));
    }

}
