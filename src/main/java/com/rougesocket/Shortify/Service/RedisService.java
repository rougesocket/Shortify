package com.rougesocket.Shortify.Service;

import com.rougesocket.Shortify.Dto.UrlCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class RedisService {

    private final RedisTemplate<String, UrlCache> redisTemplate;

    private final String PREFIX;

    @Autowired
    public RedisService(RedisTemplate<String, UrlCache> redisTemplate,

                        @Value("${shortify.redis.url-prefix}") String PREFIX){
        this.redisTemplate = redisTemplate;
        this.PREFIX = PREFIX;
    }
    public void set(String shortCode,UrlCache urlCache) {
        Duration ttl = Duration.between(
                LocalDateTime.now(),
                urlCache.getExpiresAt()
        );

        if(ttl.isNegative() || ttl.isZero())return ;
        redisTemplate.opsForValue().set(
                PREFIX+shortCode,
                urlCache,
                ttl
        );
    }

    public UrlCache get(String key){
        return redisTemplate.opsForValue().get(PREFIX+key);
    }
}
