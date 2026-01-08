package com.rougesocket.Shortify.Service;

public interface UrlService {

    String shortenUrl(String longUrl);
    String getOriginalUrl(String shortCode);
}
