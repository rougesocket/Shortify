package com.rougesocket.Shortify.Controller;

import com.rougesocket.Shortify.Dto.UrlRequest;
import com.rougesocket.Shortify.Dto.UrlResponse;
import com.rougesocket.Shortify.Service.AnalyticsService.AnalyticsService;
import com.rougesocket.Shortify.Service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@RestController
public class UrlController {

    private final UrlService urlService;

    private final AnalyticsService analyticsService;
    @Autowired
    public UrlController(UrlService urlService,AnalyticsService analyticsService){
        this.urlService=urlService;
        this.analyticsService=analyticsService;
    }

    @PostMapping("/api/url/shorten")
    public UrlResponse shortenUrl(@RequestBody UrlRequest urlRequest){
        String shortUrl = urlService.shortenUrl(urlRequest.getLongUrl());
        return new UrlResponse(shortUrl);
    }

    @GetMapping("{shortcode}")
    public void redirect(@PathVariable String shortcode, HttpServletResponse response, HttpServletRequest request) throws IOException {
        String longUrl = urlService.getOriginalUrl(shortcode);
        analyticsService.recordClick(shortcode,request.getRemoteAddr(),request.getHeader("User-Agent"),request.getHeader("Referer"));
        response.sendRedirect(longUrl);
    }

    @GetMapping("/api/analytics/{shortCode}/total")
    public long totalClicks(@PathVariable String shortCode){
        return analyticsService.getTotalClicks(shortCode);
    }

    @GetMapping("/api/analytics/{shortCode}/daily")
    public Map<LocalDate, Long> dailyClicks(@PathVariable String shortCode) {
        return analyticsService.getDailyClicks(shortCode);
    }
}
