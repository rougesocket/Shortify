package com.rougesocket.Shortify.Dto;

import java.time.LocalDateTime;

public class UrlCache {

    private String longUrl;
    private LocalDateTime expiresAt;


    public UrlCache() {
    }

    public UrlCache(String longUrl, LocalDateTime expiresAt) {
        this.longUrl = longUrl;
        this.expiresAt = expiresAt;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Override
    public String toString() {
        return "UrlCache{" +
                "longUrl='" + longUrl + '\'' +
                ", expiresAt=" + expiresAt +
                '}';
    }
}
