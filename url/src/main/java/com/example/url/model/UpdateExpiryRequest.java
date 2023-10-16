package com.example.url.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateExpiryRequest {

    @JsonProperty("days")
    private int days;

    @JsonProperty("shortUrl")
    private String shortUrl;
}
