package io.codelex.urlshortener.controllers;

import io.codelex.urlshortener.responses.StatisticsResponse;
import io.codelex.urlshortener.responses.UrlResponse;
import io.codelex.urlshortener.requests.ShortUrlRequest;
import io.codelex.urlshortener.services.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
class UrlController {
    private final UrlService services;

    public UrlController(UrlService services) {
        this.services = services;
    }

    @PostMapping("/shortened-urls/shorten")
    @ResponseStatus(HttpStatus.CREATED)
    public UrlResponse addUrl(@RequestBody ShortUrlRequest requestBody){
        return services.addUrl(requestBody);
    }

    @GetMapping("/statistics/shortened-urls")
    public StatisticsResponse getAllUrlStats(){
        return services.getAllUrlStats();
    }

    @GetMapping("/statistics/shortened-urls/{id}")
    public StatisticsResponse getUrlConsumptionCount(@PathVariable("id") Long urlId){
        return services.getUrlConsumptionCount(urlId);
    }

}
