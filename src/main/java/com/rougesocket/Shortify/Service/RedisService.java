package com.rougesocket.Shortify.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rougesocket.Shortify.Dto.UrlCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    private final ObjectMapper objectMapper;
    private final String PREFIX;

    @Autowired
    public RedisService(RedisTemplate<String, String> redisTemplate,
                        ObjectMapper objectMapper,
                        @Value("${shortify.redis.url-prefix}") String PREFIX){
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
        this.PREFIX = PREFIX;
    }
    public void set(String shortCode,UrlCache urlCache) {
        Duration ttl = Duration.between(
                LocalDateTime.now(),
                urlCache.getExpiresAt()
        );
        if(ttl.isNegative() || ttl.isZero())return ;
        try {
            redisTemplate.opsForValue().set(
                    PREFIX+shortCode,
                    objectMapper.writeValueAsString(urlCache),
                    ttl
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public UrlCache get(String key){
        String data = redisTemplate.opsForValue().get(PREFIX+key);
        if(data==null)return null;
        try {
            return objectMapper.readValue(data, UrlCache.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
