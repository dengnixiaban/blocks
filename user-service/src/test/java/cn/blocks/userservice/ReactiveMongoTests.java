package cn.blocks.userservice;

import cn.blocks.userservice.repository.dao.UserEntityRepository;
import cn.blocks.userservice.repository.po.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description
 *          reactive mongo test
 *
 * @author Somnus丶y
 * @date 2019/9/24 14:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactiveMongoTests {

    @Autowired
    private UserEntityRepository userEntityRepository;


    @Test
    public void test1(){
        Flux<UserEntity> all = userEntityRepository.findAll();
        List<UserEntity> collect = all.toStream().collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test2(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("222");
        Flux<UserEntity> insert = userEntityRepository.insert(Mono.just(userEntity));
        UserEntity resp = insert.blockFirst();
        System.out.println(resp);
    }


}
