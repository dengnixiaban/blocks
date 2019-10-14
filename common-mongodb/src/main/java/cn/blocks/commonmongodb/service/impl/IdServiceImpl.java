package cn.blocks.commonmongodb.service.impl;

import cn.blocks.commonmongodb.model.po.BaseTimePO;
import cn.blocks.commonmongodb.service.IdService;
import cn.blocks.commonutils.exception.BusinessException;
import cn.blocks.commonutils.exception.ExceptionEnum;
import cn.blocks.commonutils.utils.LogUtils;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import lombok.extern.slf4j.Slf4j;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import java.util.Objects;

import static cn.blocks.commonmongodb.constant.MongoConstant.*;

/**
 * @description
 *          mongodb id序列服务
 *
 * @auther Somnus丶y
 * @date 2019/10/14 16:44
 */
@Slf4j
public class IdServiceImpl implements IdService {

    @Autowired
    private MongoTemplate mongoTemplate;

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
    @Override
    public synchronized Long getSeqId(Class<? extends BaseTimePO> cls) {
        Document annotation = AnnotationUtils.findAnnotation(cls, Document.class);
        if(Objects.isNull(annotation)){
            throw new BusinessException(ExceptionEnum.IDSEQERROR);
        }
        String value = annotation.value();
        if(StringUtils.isEmpty(value)){
            value = cls.getSimpleName();
        }
        String idDocName = value+IDSUFFIX;
        MongoCollection<org.bson.Document> collection = mongoTemplate.getCollection(idDocName);
        FindIterable<org.bson.Document> documents = collection.find();
        MongoCursor<org.bson.Document> iterator = documents.iterator();
        if(Objects.isNull(iterator)||!iterator.hasNext()){
            LogUtils.info("mongodb-seq-id未初始化,doc:%s",idDocName);
            //init
            org.bson.Document document = new org.bson.Document();
            document.put(SEQKEY, SEQINIT);
            collection.insertOne(document);
            return SEQINIT;
        }
        org.bson.Document next = iterator.next();
        Long id = next.getLong(SEQKEY);
        if(Objects.isNull(id)){
            throw new BusinessException(ExceptionEnum.IDSEQERROR);
        }
        Long newId = id+SEQSCALE;
        Bson update =  new org.bson.Document(SYMBOL_SET,
                new org.bson.Document().append(SEQKEY, newId));
        collection.findOneAndUpdate(next,update);
        return newId;
    }




}
