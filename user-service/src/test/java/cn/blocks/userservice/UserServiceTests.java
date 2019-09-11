package cn.blocks.userservice;

import cn.blocks.userservice.repository.po.UserPO;
import cn.blocks.userservice.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description
 *          test for userservice
 *
 * @auther Somnus丶y
 * @date 2019/9/10 19:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void saveTest(){
        UserPO one = new UserPO();
        one.setAccount("blocks1");
        one.setPassword("123456");
        one.setDesc("积木零号用户");
        one.setGender(-1);
        userService.saveOne(one);
    }

}
