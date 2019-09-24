package cn.blocks.userservice.repository.dao;

import cn.blocks.userservice.repository.po.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @description
 *          reactive mongo test
 *
 * @author Somnus丶y
 * @date 2019/9/24 14:57
 */
@Repository
public interface UserEntityRepository extends ReactiveMongoRepository<UserEntity, Long> {
}
