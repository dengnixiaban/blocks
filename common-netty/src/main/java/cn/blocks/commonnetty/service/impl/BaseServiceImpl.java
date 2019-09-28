package cn.blocks.commonnetty.service.impl;

import cn.blocks.commonnetty.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/9/28 14:52
 */
@Service
public class BaseServiceImpl implements BaseService {
    @Override
    public void test() {
        System.out.println("调用service服务");
    }
}