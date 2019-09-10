package cn.blocks.userservice.repository.dao;

import cn.blocks.userservice.repository.po.UserPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/9/10 18:56
 */
@Mapper
public interface UserMapper {

    @Select("select * from t_users where id = #{id}")
    UserPO queryById(@Param("id") Long id);


    @Insert("insert (id,account,nickName,desc,gender,icon,password,createTime,modifyTime,deleteTime,modifyId) values "
            + "(#{id},#{account},#{nickName},#{desc},#{gender},#{icon},#{password},#{createTime},"
            + "#{modifyTime},#{deleteTime},#{modifyId})")
    Long saveOne(UserPO user);

}
