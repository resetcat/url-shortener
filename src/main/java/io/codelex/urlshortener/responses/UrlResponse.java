package io.codelex.urlshortener.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

public record UrlResponse(Long url, @JsonFormat(pattern = "yyyy-MM-dd HH:mm") @JsonInclude(
        JsonInclude.Include.NON_NULL) LocalDateTime expires) {

    public UrlResponse(Long url, LocalDateTime expires) {
        this.url = url;
        this.expires = expires;
    }

    @Override
    public LocalDateTime expires() {
        return expires;
    }
}
