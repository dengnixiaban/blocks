package cn.blocks.userservice.repository.dao;

import cn.blocks.userservice.repository.po.UserPO;
import org.apache.ibatis.annotations.*;

/**
 * @description
 * @author Somnus丶y
 * @date 2019/9/10 18:56
 */
@Mapper
public interface UserMapper {

    @Select("select * from t_users where id = #{id}")
    UserPO queryById(@Param("id") Long id);


    @Insert("insert into t_users (`account`,`nickName`,`desc`,`gender`,`icon`,`password`,`createTime`,`modifyTime`,"
            + "`deleteTime`,`modifyId`) "
            + "values "
            + "(#{account,jdbcType=VARCHAR},#{nickName,jdbcType=VARCHAR},#{desc,jdbcType=VARCHAR},"
            + "#{gender,jdbcType=INTEGER},#{icon,jdbcType=VARCHAR},#{password,jdbcType=VARCHAR},#{createTime,jdbcType=TIMESTAMP},"
            + "#{modifyTime,jdbcType=TIMESTAMP},#{deleteTime,jdbcType=TIMESTAMP},#{modifyId,jdbcType=INTEGER})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
// 非自增主键返回形式   @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", resultType = Long.class, before = false)
    Long saveOne(UserPO user);

}
