package com.example.url.service;

import com.example.url.algorithm.UrlShorteningAlgorithm;
import com.example.url.db.DBHelper;
import com.example.url.model.ShortenUrlRequest;
import com.example.url.model.UpdateExpiryRequest;
import com.example.url.model.UpdateShortenUrlRequest;
import com.example.url.model.UrlTableEntry;
import com.example.url.repository.UrlCrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UrlShorteningServiceImpl implements UrlShorteningService{

    @Autowired
    UrlShorteningAlgorithm crc32Algorithm;

    @Autowired
    DBHelper dbHelper;

    @Override
    public String shortenUrl(ShortenUrlRequest request) {
        String shortUrl = crc32Algorithm.shorten(request.getLongUrl());
        dbHelper.persistWithHibernate(request, shortUrl);
        return shortUrl;
    }


    @Override
    public String getLongUrl(String shortUrl) {
        return dbHelper.getLongUrl(shortUrl);

    }

    @Override
    public Boolean updateShortenUrl(UpdateShortenUrlRequest request) {
        return dbHelper.updateShortUrl(request.getShortUrl(), request.getLongUrl());
    }

    @Override
    public Boolean updateExpiry(UpdateExpiryRequest request) {
        return dbHelper.updateExpiry(request.getShortUrl(), request.getDays());
    }


}
