package cn.blocks.webadmin.remote.normal;

import cn.blocks.userapi.service.IUserService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/8/30 18:48
 */
@FeignClient(value = "user-service")
public interface FeignUserService extends IUserService {

}
