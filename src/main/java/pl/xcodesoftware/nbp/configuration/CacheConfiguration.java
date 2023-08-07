package pl.xcodesoftware.nbp.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfiguration {

    private final CacheManager cacheManager;

    @Scheduled(cron = "*/30 * * * * *")
    public void clearCacheEvery30Seconds() {
        log.info("Clearing cache...");
        for (String cacheKey : cacheManager.getCacheNames()) {
            cacheManager.getCache(cacheKey).clear();
        }
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

}
