package cn.blocks.userservice.service;

import cn.blocks.userapi.model.UserDTO;
import cn.blocks.userservice.repository.po.UserPO;

/**
 * @description
 *          user service
 *
 * @author Somnus丶y
 * @date 2019/9/10 19:11
 */
public interface UserService {


    UserPO queryById(Long id);

    UserDTO saveOne(UserPO user);

}
