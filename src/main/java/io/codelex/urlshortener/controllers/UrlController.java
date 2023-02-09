package io.codelex.urlshortener.controllers;

import io.codelex.urlshortener.models.UrlResponse;
import io.codelex.urlshortener.requests.ShortUrlRequest;
import io.codelex.urlshortener.services.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
class UrlController {
    UrlService services;

    public UrlController(UrlService services) {
        this.services = services;
    }

    @PostMapping("/shortened-urls/shorten")
    @ResponseStatus(HttpStatus.CREATED)
    public UrlResponse addUrl(@RequestBody ShortUrlRequest requestBody){
        return services.addUrl(requestBody);
    }
}
