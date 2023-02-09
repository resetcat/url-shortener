package io.codelex.urlshortener.services;

import io.codelex.urlshortener.models.ShortUrl;
import io.codelex.urlshortener.models.UrlResponse;
import io.codelex.urlshortener.repositories.UrlRepository;
import io.codelex.urlshortener.requests.ShortUrlRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UrlService {

    UrlRepository repository;

    @Value("${shorturl.default.expire.days}")
    private int defaultExpDays;


    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public UrlResponse addUrl(ShortUrlRequest requestBody) {
        validateUrl(requestBody.getUrl());
        ShortUrl shortUrl = new ShortUrl(requestBody);
        repository.save(shortUrl);
        return new UrlResponse(shortUrl.getUrl(), shortUrl.getExpires());
    }

    private void validateUrl(String url) {
        System.out.println(url);
        if (url != null) {
            Pattern pattern = Pattern.compile(
                    "^(https?:\\/?\\/?)?([.?\\w\\Q$-_+!*'(),%\\E]+\\.)+(\\w{2,63})" + "(:\\d{1," +
                            "4})?([\\w\\Q/$-_+!*'(),%\\E]+\\.?[\\w])*\\/?$");
            Matcher matcher = pattern.matcher(url);
            if (!matcher.matches()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


    public void resetAll() {
        repository.deleteAll();
    }
}
