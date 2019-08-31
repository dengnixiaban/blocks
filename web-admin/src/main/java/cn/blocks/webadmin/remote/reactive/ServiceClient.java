package cn.blocks.webadmin.remote.reactive;

import cn.blocks.userapi.model.UserDTO;
import feign.Headers;
import feign.RequestLine;
import reactor.core.publisher.Flux;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/8/31 9:59
 */
@Headers({ "Accept: application/json" })
public interface ServiceClient {

    @RequestLine("GET /")
    Flux<UserDTO> list();

}