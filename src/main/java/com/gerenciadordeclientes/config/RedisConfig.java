package com.gerenciadordeclientes.config;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@EnableCaching
@Configuration
public class RedisConfig {

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return builder -> {
            configureCache(builder, "buscarPorNome", 10);
            configureCache(builder, "buscarClienteId", 5);
            configureCache(builder, "buscarVeiculos", 5);
            configureCache(builder, "buscarPorVencimento", 5);
            configureCache(builder, "buscarClientes", 5);
        };
    }

    private void configureCache(RedisCacheManager.RedisCacheManagerBuilder builder, String cacheName, long ttlMinutes) {
        builder.withCacheConfiguration(cacheName,
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(ttlMinutes)));
    }
}
