package com.example.url.db;

import com.example.url.exception.UrlShortnerException;
import com.example.url.model.ShortenUrlRequest;
import com.example.url.model.UrlTableEntry;
import com.example.url.repository.UrlCrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Date;

@Component
public class DBHelper {

    @Autowired
    UrlCrudRepository urlCrudRepository;

    public void persistWithHibernate(ShortenUrlRequest request, String shortUrl){
        boolean exists = urlCrudRepository.existsByShortURL(shortUrl);
        if(!exists){
            System.out.println("Starting Transaction");
            UrlTableEntry entry = new UrlTableEntry(request.getLongUrl(), shortUrl, request.getExpireAfterDays());
            urlCrudRepository.save(entry);
        }
    }

    public String getLongUrl(String shortUrl){
        UrlTableEntry urlTableEntry = urlCrudRepository.findByShortURL(shortUrl);
        System.out.println(urlTableEntry.getLongURL());
        return urlTableEntry.getLongURL();
    }

    public Boolean updateShortUrl(String shortUrl, String longUrl){
        UrlTableEntry urlTableEntry = urlCrudRepository.findByShortURL(shortUrl);
        urlTableEntry.setLongURL(longUrl);
        urlCrudRepository.save(urlTableEntry);
        return true;
    }

    public Boolean updateExpiry(String shortUrl, int days){
        UrlTableEntry urlTableEntry = urlCrudRepository.findByShortURL(shortUrl);
        Date newExpiryDate = urlTableEntry.getExpirationDate(urlTableEntry.getExpirationDate(),days);
        urlTableEntry.setExpirationDate(newExpiryDate);
        urlCrudRepository.save(urlTableEntry);
        return true;
    }

}
