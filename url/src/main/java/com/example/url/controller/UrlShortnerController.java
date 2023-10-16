package com.example.url.controller;

import com.example.url.model.ShortenUrlRequest;
import com.example.url.model.ShortenUrlResponse;
import com.example.url.model.UpdateExpiryRequest;
import com.example.url.model.UpdateShortenUrlRequest;
import com.example.url.service.UrlShorteningServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class UrlShortnerController {

    private final Integer DEFAULT_EXPIRY_DAYS = 30;

    @Autowired
    UrlShorteningServiceImpl urlShorteningService;

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping("/url/shorten")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request){

        if(Objects.isNull(request))
            throw new RuntimeException("shortenUrl request is null");

        if(request.getExpireAfterDays() == null)
            request.setExpireAfterDays(DEFAULT_EXPIRY_DAYS);

        return new ResponseEntity<>(new ShortenUrlResponse(urlShorteningService.shortenUrl(request)),HttpStatus.OK);
    }

    @GetMapping("/shortUrl/{shortUrl}")
    public void redirectUrl(@PathVariable String shortUrl, HttpServletResponse response){
        String longUrl = urlShorteningService.getLongUrl(shortUrl);
        response.setHeader("Location",longUrl);
        response.setStatus(302);
    }

    @PostMapping("/url/update/shortUrl")
    public ResponseEntity<Boolean> updateShortUrl(@RequestBody UpdateShortenUrlRequest request){

        if(Objects.isNull(request))
            throw new RuntimeException("update shortenUrl request is null");

        return new ResponseEntity<>(urlShorteningService.updateShortenUrl(request),HttpStatus.OK);
    }

    @PostMapping("/url/update/expiry")
    public ResponseEntity<Boolean> updateExpiry(@RequestBody UpdateExpiryRequest request){

        if(Objects.isNull(request))
            throw new RuntimeException("update expiry request is null");

        return new ResponseEntity<>(urlShorteningService.updateExpiry(request),HttpStatus.OK);
    }
}
