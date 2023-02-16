package io.codelex.urlshortener.controllers;

import io.codelex.urlshortener.services.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
class RedirectController {
    private final UrlService service;

    public RedirectController(UrlService service) {
        this.service = service;
    }

    @GetMapping("/{id:\\d+}")
    public RedirectView redirectShortUrl(@PathVariable("id") Long id) {
        String originalUrl = service.getOriginalUrlbyId(id);
        originalUrl = service.addHttps(originalUrl);
        service.consumeUrl(id);
        RedirectView redirectView = new RedirectView(originalUrl);
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }
}
