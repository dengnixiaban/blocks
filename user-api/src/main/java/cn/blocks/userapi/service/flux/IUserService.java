package cn.blocks.userapi.service.flux;

import cn.blocks.userapi.model.UserDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Flux;

/**
 * @description
 *          用户服务API
 *
 * @author Somnus丶y
 * @date 2019/8/30 17:37
 */
@RequestMapping(value = "/user")
public interface IUserService {


    @RequestMapping(value = "/user-info",method = RequestMethod.GET)
    Flux<UserDTO> userInfo(UserDTO userDTO);

}
