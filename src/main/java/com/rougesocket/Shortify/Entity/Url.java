package com.rougesocket.Shortify.Entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "urls",indexes = {@Index( name = "idx_short_code",columnList = "short_code")})
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_code",nullable = false,unique = true,length = 10)
    private String shortCode;

    @Column(name = "long_url",nullable = false,length = 2048)
    private String longUrl;

    @Column(name = "click_count",nullable = false)
    private Long clickCount = 0L;

    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    public Url() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isExpired(){
        return this.expiresAt.isBefore(LocalDateTime.now());
    }
    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", shortCode='" + shortCode + '\'' +
                ", longUrl='" + longUrl + '\'' +
                ", clickCount=" + clickCount +
                ", createdAt=" + createdAt +
                ", expiresAt=" + expiresAt +
                '}';
    }
}
