package cn.blocks.userapi.service.normal;

import cn.blocks.commonutils.model.BaseResp;
import cn.blocks.userapi.model.UserDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description
 *          用户服务API
 *
 * @auther Somnus丶y
 * @date 2019/8/30 17:37
 */
@RequestMapping(value = "/user")
public interface IUserService {


    @RequestMapping(value = "/user-info",method = RequestMethod.GET)
    BaseResp<UserDTO> userInfo(UserDTO userDTO);

}
