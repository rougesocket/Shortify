package com.rougesocket.Shortify.Dto;

public class UrlResponse {

    private String shortUrl;

    public UrlResponse() {
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public UrlResponse(String shortUrl) {

        this.shortUrl = shortUrl;
    }

    @Override
    public String toString() {
        return "UrlResponse{" +
                "shortUrl='" + shortUrl + '\'' +
                '}';
    }
}
