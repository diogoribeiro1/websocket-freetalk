package com.freetalk.freetalk.config;

import java.time.Duration;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
@EnableScheduling
public class RedisConfiguration {

    public static final String LAST_MESSAGES_KEY = "lastMessages";
    public static final String LAST_GROUP_MESSAGES_KEY = "lastGroupMessages";

    @CacheEvict(allEntries = true, value = LAST_MESSAGES_KEY)
    @Scheduled(fixedDelayString = "${cache.ttl.ms:60000}")
    public void evictLastMessagesCache() {
        System.out.println("Cache 'lastMessages' cleared");
    }

    @CacheEvict(allEntries = true, value = LAST_GROUP_MESSAGES_KEY)
    @Scheduled(fixedDelayString = "${cache.ttl.ms:60000}")
    public void evictLastGroupMessagesCache() {
        System.out.println("Cache 'lastGroupMessages' cleared");
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10)) // TTL de 10 minutos para cada cache
                .disableCachingNullValues()
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(
                                new GenericJackson2JsonRedisSerializer()));
    }
}
