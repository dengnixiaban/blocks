package cn.blocks.commonutils.utils;

import org.springframework.lang.Nullable;

import java.util.Collection;

/**
 * @description
 *          集合操作utils
 *      <p>
 *       extends   {@link org.springframework.util.CollectionUtils}
 *
 * @author Somnus丶y
 * @date 2019/9/2 9:21
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils {



    /**
     * @description
     *          集合不为空
     *
     * @param collection
     * @return boolean
     * @throws
     * @author Somnus丶y
     * @date 2019/9/2
     */
    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return !isEmpty(collection);
    }


}
