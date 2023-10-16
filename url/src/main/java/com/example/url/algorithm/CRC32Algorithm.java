package com.example.url.algorithm;

import org.springframework.stereotype.Component;

import java.util.zip.CRC32;

@Component
public class CRC32Algorithm implements UrlShorteningAlgorithm {
    @Override
    public String shorten(String longUrl) {
        CRC32 crc = new CRC32();
        crc.update(longUrl.getBytes());
        return Long.toHexString(crc.getValue());
    }
}
