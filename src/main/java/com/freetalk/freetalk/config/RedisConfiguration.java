package com.freetalk.freetalk.config;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

// @Slf4j
@Configuration
@EnableCaching
@EnableScheduling
public class RedisConfiguration {
    public static final String NEWS_KEY = "news";

    @CacheEvict(allEntries = true, value = NEWS_KEY)
    @Scheduled(fixedDelayString = "${cache.ttl.ms:60000}")
    public void evictCache() {
        System.out.println("Cache cleared");
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }
}
