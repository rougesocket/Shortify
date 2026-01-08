package com.rougesocket.Shortify.Controller;

import com.rougesocket.Shortify.Dto.UrlRequest;
import com.rougesocket.Shortify.Dto.UrlResponse;
import com.rougesocket.Shortify.Service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService){
        this.urlService=urlService;
    }

    @PostMapping("/api/url/shorten")
    public UrlResponse shortenUrl(@RequestBody UrlRequest urlRequest){
        String shortUrl = urlService.shortenUrl(urlRequest.getLongUrl());
        return new UrlResponse(shortUrl);
    }

    @GetMapping("{shortcode}")
    public void redirect(@PathVariable String shortcode, HttpServletResponse response) throws IOException {
        String longUrl = urlService.getOriginalUrl(shortcode);
        response.sendRedirect(longUrl);
    }
}
