package com.example.url.service;

import com.example.url.model.ShortenUrlRequest;
import com.example.url.model.UpdateExpiryRequest;
import com.example.url.model.UpdateShortenUrlRequest;

public interface UrlShorteningService {

    String shortenUrl(ShortenUrlRequest request);

    String getLongUrl(String shortUrl);

    Boolean updateShortenUrl(UpdateShortenUrlRequest request);

    Boolean updateExpiry(UpdateExpiryRequest request);
}
