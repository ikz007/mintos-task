package org.example.weatherforecast.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;
import lombok.extern.slf4j.Slf4j;
import org.example.weatherforecast.util.WeatherConstants;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
@EnableCaching
public class CacheConfiguration extends CachingConfigurerSupport {

    @Bean
    public CaffeineCache weatherCache() {
        return new CaffeineCache(WeatherConstants.WEATHER_CACHE_NAME,
                Caffeine.newBuilder()
                        .expireAfterWrite(WeatherConstants.WEATHER_CACHE_TIMEOUT_IN_HOURS, TimeUnit.HOURS)
                        .build()
        );
    }

    @Bean
    public CaffeineCache locationCache() {
        return new CaffeineCache(WeatherConstants.LOCATION_CACHE_NAME,
                Caffeine.newBuilder()
                        .expireAfterWrite(WeatherConstants.LOCATION_CACHE_TIMEOUT_IN_HOURS, TimeUnit.HOURS)
                        .build()
        );
    }

    @Bean
    public Ticker ticker() {
        return Ticker.systemTicker();
    }
}
