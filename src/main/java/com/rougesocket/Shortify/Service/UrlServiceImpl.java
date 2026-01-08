package com.rougesocket.Shortify.Service;

import com.rougesocket.Shortify.Model.UrlMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UrlServiceImpl implements UrlService{

    @Value("${shortify.base-url}")
    private String BASE_URL;

    private Map<String ,UrlMapping> mp = new HashMap<>();

    @Override
    public String shortenUrl(String longUrl){
        String shortCode = generateShortUrl(longUrl);
        UrlMapping urlMapping = new UrlMapping(longUrl,shortCode,0L);
        mp.put(shortCode,urlMapping);
        return BASE_URL+shortCode;
    }

    @Override
    public String getOriginalUrl(String shortCode) {
        if(mp.containsKey(shortCode))return mp.get(shortCode).getLongUrl();
        return null;
    }

    private String generateShortUrl(String longUrl){

        String candidates = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder();
        for(int i=0;i<8;i++)shortUrl.append(candidates.charAt(random.nextInt(candidates.length())));
        return shortUrl.toString();
    }
}
