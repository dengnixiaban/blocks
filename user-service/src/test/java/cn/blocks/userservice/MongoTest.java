package cn.blocks.userservice;

import cn.blocks.commonmongodb.service.IdService;
import cn.blocks.userservice.repository.po.PlatformPO;
import cn.blocks.userservice.repository.po.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description
 *          mongo template
 *
 * @author Somnus丶y
 * @date 2019/9/24 11:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IdService idService;


    @Test
    public void test1(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("111");
        mongoTemplate.insert(userEntity);
    }


    @Test
    public void test2(){
        Long seqId = idService.getSeqId(PlatformPO.class);
        System.out.println(seqId);
        seqId = idService.getSeqId(PlatformPO.class);
        System.out.println(seqId);
    }

}
