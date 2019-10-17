package cn.blocks.commonmysql.model.po;

import lombok.Data;

/**
 * @author jianghuaidi
 * @email jianghuaidi@szdxsoft.com
 * @date 2019/10/14
 */
@Data
public class BaseQuery {
    private int currentPage = 1;
    private int pageSize = 10;
}
