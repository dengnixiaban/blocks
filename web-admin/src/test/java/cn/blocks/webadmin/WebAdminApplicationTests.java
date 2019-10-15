package cn.blocks.webadmin;

import cn.blocks.commonutils.model.BaseResp;
import cn.blocks.userapi.model.UserDTO;
import cn.blocks.webadmin.remote.normal.FeignUserService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebAdminApplicationTests {

    @Autowired
    private FeignUserService userService;

    @Test
    public void contextLoads() {
        BaseResp<UserDTO> resp = userService.userInfo(new UserDTO());
        System.out.println(JSON.toJSONString(resp));
    }

}
