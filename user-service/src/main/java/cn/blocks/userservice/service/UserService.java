package cn.blocks.userservice.service;

import cn.blocks.userservice.repository.po.UserPO;

/**
 * @description
 *          user service
 *
 * @auther Somnus丶y
 * @date 2019/9/10 19:11
 */
public interface UserService {


    UserPO queryById(Long id);

    Long saveOne(UserPO user);

}
