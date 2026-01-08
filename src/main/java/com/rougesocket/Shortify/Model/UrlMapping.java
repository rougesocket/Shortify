package com.rougesocket.Shortify.Model;

public class UrlMapping {

    private String longUrl;
    private String shorturl;
    private Long clickCount;

    public UrlMapping() {
    }

    public UrlMapping(String longUrl, String shorturl, Long clickCount) {
        this.longUrl = longUrl;
        this.shorturl = shorturl;
        this.clickCount = clickCount;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShorturl() {
        return shorturl;
    }

    public void setShorturl(String shorturl) {
        this.shorturl = shorturl;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    @Override
    public String toString() {
        return "UrlMapping{" +
                "longUrl='" + longUrl + '\'' +
                ", shorturl='" + shorturl + '\'' +
                ", clickCount=" + clickCount +
                '}';
    }
}
