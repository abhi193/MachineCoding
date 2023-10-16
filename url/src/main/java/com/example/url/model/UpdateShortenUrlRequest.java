package com.example.url.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateShortenUrlRequest {

    @JsonProperty("longUrl")
    private String longUrl;

    @JsonProperty("shortUrl")
    private String shortUrl;
}
