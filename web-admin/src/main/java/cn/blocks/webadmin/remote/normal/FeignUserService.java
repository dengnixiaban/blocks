package cn.blocks.webadmin.remote.normal;

import cn.blocks.userapi.service.normal.IUserService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description
 * @author Somnus丶y
 * @date 2019/8/30 18:48
 */
@FeignClient(value = "user-service")
public interface FeignUserService extends IUserService {

}
