package io.codelex.urlshortener.controllers;

import io.codelex.urlshortener.services.UrlService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
class TestController {
    private final UrlService services;

    public TestController(UrlService services) {
        this.services = services;
    }

    @PostMapping("/reset")
    public void clearUrls() {
        services.resetAll();
    }
}
