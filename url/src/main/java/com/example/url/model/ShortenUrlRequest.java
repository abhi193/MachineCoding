package com.example.url.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Getter
@Setter
public class ShortenUrlRequest {

    @JsonProperty("longUrl")
    private String longUrl;

    @JsonProperty("expireAfterDays")
    private Integer expireAfterDays;
}
