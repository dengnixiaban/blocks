package cn.blocks.userservice.service.impl;

import cn.blocks.userservice.repository.dao.UserMapper;
import cn.blocks.userservice.repository.po.UserPO;
import cn.blocks.userservice.service.UserService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description
 *          用户服务
 *
 * @auther Somnus丶y
 * @date 2019/9/10 19:12
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserPO queryById(@NonNull Long id) {
        return userMapper.queryById(id);
    }

    @Override
    public Long saveOne(UserPO user) {
        return userMapper.saveOne(user);
    }

}
