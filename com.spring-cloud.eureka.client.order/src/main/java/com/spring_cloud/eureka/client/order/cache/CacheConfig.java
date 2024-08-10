package com.spring_cloud.eureka.client.order.cache;



import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(defaultCacheConfiguration())
//                .withInitialCacheConfigurations(customConfigurationMap())
                .build();
    }
    private RedisCacheConfiguration defaultCacheConfiguration() {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(60))// 10초간 캐시 데이터 유지
                .computePrefixWith(CacheKeyPrefix.simple())
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.java())
                );
    }
}
