package cn.blocks.commonmongodb.config;

import cn.blocks.commonmongodb.service.IdService;
import cn.blocks.commonmongodb.service.impl.IdServiceImpl;
import com.google.common.collect.Lists;
import com.mongodb.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.ReactiveMongoClientFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.*;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description
 *          单机mongo 配置
 *
 * @author Somnus丶y
 * @date 2019/9/24 11:42
 */
@Configuration
@ConditionalOnProperty(value = "blocks.mongo.singleton.enable",havingValue = "true")
@EnableConfigurationProperties(SingleMongoProperties.class)

@Import(MongoDataConfiguration.class)
@AutoConfigureAfter(MongoReactiveAutoConfiguration.class)
public class SingleMongoConfiguration {

    @Autowired
    private SingleMongoProperties properties;

    private com.mongodb.reactivestreams.client.MongoClient mongo;

    @Autowired
    private ObjectProvider<MongoClientSettings> settings;



    /**
     * @description
     *          template
     *
     * @param mongoDbFactory
     * @return org.springframework.data.mongodb.core.MongoTemplate
     * @throws
     * @author Somnus丶y
     * @date 2019/9/24
     */
    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory,
                                       MappingMongoConverter mappingMongoConverter){
        return new MongoTemplate(mongoDbFactory,mappingMongoConverter);
    }

    /**
     * @description
     *          factory
     *
     * @return org.springframework.data.mongodb.MongoDbFactory
     * @throws
     * @author Somnus丶y
     * @date 2019/9/24
     */
    @Bean
    public MongoDbFactory mongoDbFactory() {
        //客户端配置（连接数，副本集群验证）
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.connectionsPerHost(properties.getMaxConnectionsPerHost());
        builder.minConnectionsPerHost(properties.getMinConnectionsPerHost());
        builder.writeConcern(WriteConcern.UNACKNOWLEDGED);
        builder.threadsAllowedToBlockForConnectionMultiplier(
                properties.getThreadsAllowedToBlockForConnectionMultiplier());
        builder.serverSelectionTimeout(properties.getServerSelectionTimeout());
        builder.maxWaitTime(properties.getMaxWaitTime());
        builder.maxConnectionIdleTime(properties.getMaxConnectionIdleTime());
        builder.maxConnectionLifeTime(properties.getMaxConnectionLifeTime());
        builder.connectTimeout(properties.getConnectTimeout());
        builder.socketTimeout(properties.getSocketTimeout());
        builder.sslEnabled(properties.getSslEnabled());
        builder.sslInvalidHostNameAllowed(properties.getSslInvalidHostNameAllowed());
        builder.alwaysUseMBeans(properties.getAlwaysUseMBeans());
        builder.heartbeatFrequency(properties.getHeartbeatFrequency());
        builder.minHeartbeatFrequency(properties.getMinHeartbeatFrequency());
        builder.heartbeatConnectTimeout(properties.getHeartbeatConnectTimeout());
        builder.heartbeatSocketTimeout(properties.getHeartbeatSocketTimeout());
        MongoClientOptions mongoClientOptions = builder.build();
        // MongoDB地址列表
        List<ServerAddress> serverAddresses = Lists.newLinkedList();
        String[] hostAndPort = properties.getAddress().split(":");
        String host = hostAndPort[0];
        Integer port = Integer.parseInt(hostAndPort[1]);
        ServerAddress serverAddress = new ServerAddress(host, port);
        serverAddresses.add(serverAddress);
        MongoCredential credential =null;
        //是否配置了密码
        if(StringUtils.isNotEmpty(properties.getUsername()) && StringUtils.isNotEmpty(properties.getPassword())){
            credential = MongoCredential.createScramSha1Credential(properties.getUsername(), properties.getDatabase(),
                    properties.getPassword().toCharArray());
        }
        MongoClient mongoClient = null;
        if(Objects.nonNull(credential)){
            mongoClient = new MongoClient(serverAddresses, credential, mongoClientOptions);
        }else{
            mongoClient = new MongoClient(serverAddresses, mongoClientOptions);
        }
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, properties.getDatabase());
        return mongoDbFactory;
    }



    /**
     * @description
     *          消除class
     *
     * @param mongoDbFactory, context, beanFactory
     * @return org.springframework.data.mongodb.core.convert.MappingMongoConverter
     * @throws
     * @author Somnus丶y
     * @date 2019/9/24
     */
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory mongoDbFactory,
                                                       MongoMappingContext context, BeanFactory beanFactory) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        try {
            mappingConverter.setCustomConversions(beanFactory.getBean(CustomConversions.class));
        } catch (NoSuchBeanDefinitionException ignore) {
        }

        // Don't save _class to mongo
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return mappingConverter;
    }

    //mongodb事务只支持主从模式

    /*@Bean
    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }*/




    /***********************************************reactive***************************************************/

    @Bean
    @ConditionalOnMissingBean
    public com.mongodb.reactivestreams.client.MongoClient reactiveStreamsMongoClient(
            Environment environment,
            ObjectProvider<MongoClientSettingsBuilderCustomizer> builderCustomizers,
            MongoClientSettings settings) {

        MongoProperties mongoProperties = new MongoProperties();
        String[] hostAndPort = properties.getAddress().split(":");
        String host = hostAndPort[0];
        Integer port = Integer.parseInt(hostAndPort[1]);
        mongoProperties.setHost(host);
        mongoProperties.setPort(port);
        mongoProperties.setDatabase(properties.getDatabase());
        ReactiveMongoClientFactory factory = new ReactiveMongoClientFactory(mongoProperties, environment,
                builderCustomizers.orderedStream().collect(Collectors.toList()));
        this.mongo = factory.createMongoClient(settings);
        return this.mongo;
    }

    @Bean
    @Primary
    public ReactiveMongoOperations reactiveMongoTemplate(ReactiveMongoDatabaseFactory reactiveMongoDbFactory,
                                                         MappingMongoConverter mappingMongoConverter) throws Exception {
        return new ReactiveMongoTemplate(reactiveMongoDbFactory, mappingMongoConverter);
    }

    @Bean
    public ReactiveMongoDatabaseFactory reactiveMongoDbFactory(
            com.mongodb.reactivestreams.client.MongoClient reactiveStreamsMongoClient
    ) {
        return new SimpleReactiveMongoDatabaseFactory(reactiveStreamsMongoClient, properties.getDatabase());
    }

/*
    @Bean
    public MongoClientSettings getSettings(ObjectProvider<MongoClientSettings> settings){
        return settings.getIfAvailable();
    }*/

    @PreDestroy
    public void close() {
        if (this.mongo != null) {
            this.mongo.close();
        }
    }

    @Bean
    public IdService idService(){
        return new IdServiceImpl();
    }



}
