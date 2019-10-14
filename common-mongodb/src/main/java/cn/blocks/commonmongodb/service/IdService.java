package cn.blocks.commonmongodb.service;

import cn.blocks.commonmongodb.model.po.BaseTimePO;

/**
 * @description
 *          mongodb id序列服务
 *
 * @auther Somnus丶y
 * @date 2019/10/14 16:43
 */
public interface IdService {

    /**
     * @description
     *
     *          获取序列id
     *
     * @param cls
     * @return java.lang.Long
     * @throws
     * @author Somnus丶y
     * @date 2019/10/14
     */
    Long getSeqId(Class<? extends BaseTimePO> cls);

}
