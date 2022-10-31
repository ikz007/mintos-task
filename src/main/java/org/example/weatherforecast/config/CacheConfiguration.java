package org.example.weatherforecast.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
@EnableCaching
public class CacheConfiguration extends CachingConfigurerSupport {

    private static final long CACHE_TIMEOUT_IN_HOURS = 2;
    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        log.debug("cache timeout: {}", CACHE_TIMEOUT_IN_HOURS);
        return Caffeine.newBuilder().expireAfterWrite(CACHE_TIMEOUT_IN_HOURS, TimeUnit.HOURS);
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager ccm = new CaffeineCacheManager();
        ccm.setCaffeine(caffeine);
        return ccm;
    }
}
