package cn.blocks.webadmin.remote.fallback;

import cn.blocks.commonutils.model.BaseResp;
import cn.blocks.commonutils.utils.LogUtils;
import cn.blocks.userapi.model.UserDTO;
import cn.blocks.webadmin.remote.normal.FeignUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description
 *          userfallback
 *
 * @auther Somnus丶y
 * @date 2019/10/15 10:31
 */
@Slf4j
@Component
public class FeignUserFallback implements FeignUserService {

    @Override
    public BaseResp<UserDTO> userInfo(UserDTO userDTO) {
        LogUtils.info(log,"user-service fallback");
        return new BaseResp<>();
    }

}
