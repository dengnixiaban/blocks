package cn.blocks.scheduleservice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jianghuaidi
 * @email jianghuaidi@szdxsoft.com
 * @date 2019/10/14
 */
@Data
public class BaseQuery {
    @ApiModelProperty("当前页:默认值1")
    private int currentPage = 1;
    @ApiModelProperty("每页条数:默认值10")
    private int pageSize = 10;
}
