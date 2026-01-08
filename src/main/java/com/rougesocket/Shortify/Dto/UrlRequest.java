package com.rougesocket.Shortify.Dto;

public class UrlRequest {

    private String longUrl;

    public UrlRequest() {
    }

    public UrlRequest(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    @Override
    public String toString() {
        return "UrlRequest{" +
                "longUrl='" + longUrl + '\'' +
                '}';
    }
}
