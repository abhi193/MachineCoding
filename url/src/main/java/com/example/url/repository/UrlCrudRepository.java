package com.example.url.repository;

import com.example.url.model.UrlTableEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlCrudRepository extends CrudRepository<UrlTableEntry,Integer> {

    boolean existsByShortURL(String shortUrl);
    //SpringBoot will create a query method existsByShortURL in my repository with field named shortURL
    UrlTableEntry findByShortURL(String shortUrl);

}
