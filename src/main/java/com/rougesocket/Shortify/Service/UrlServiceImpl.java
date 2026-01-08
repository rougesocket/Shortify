package com.rougesocket.Shortify.Service;

import com.rougesocket.Shortify.Entity.Url;
import com.rougesocket.Shortify.Repository.UrlRepository;
import com.rougesocket.Shortify.exception.UrlExpiredException;
import com.rougesocket.Shortify.exception.UrlNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UrlServiceImpl implements UrlService{


    @Value("${shortify.base-url}")
    private String BASE_URL;

    @Value("${shortify.url.expiry-hours}")
    private long expiry;

    private final UrlRepository urlRepository;

    @Autowired
    public UrlServiceImpl(UrlRepository urlRepository){
        this.urlRepository=urlRepository;
    }
    @Override
    public String shortenUrl(String longUrl){
        String shortCode = generateShortUrl(longUrl);
        Url url = new Url();
        url.setLongUrl(longUrl);
        url.setShortCode(shortCode);
        LocalDateTime now = LocalDateTime.now();
        url.setCreatedAt(now);
        url.setExpiresAt(now.plusHours(expiry));
        urlRepository.save(url);
        return BASE_URL+shortCode;
    }

    @Override
    @Transactional
    public String getOriginalUrl(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode).orElseThrow(()-> new UrlNotFoundException("Url Not Found: "+shortCode));
        if(url.isExpired()){
            throw new UrlExpiredException("Url Expired");
        }
        url.setClickCount(url.getClickCount()+1);
        return url.getLongUrl();
    }

    private String generateShortUrl(String longUrl){

        String candidates = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder();
        for(int i=0;i<8;i++)shortUrl.append(candidates.charAt(random.nextInt(candidates.length())));
        return shortUrl.toString();
    }
}
