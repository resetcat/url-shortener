package io.codelex.urlshortener.services;

import io.codelex.urlshortener.models.ShortUrl;
import io.codelex.urlshortener.repositories.UrlRepository;
import io.codelex.urlshortener.requests.ShortUrlRequest;
import io.codelex.urlshortener.responses.StatisticsResponse;
import io.codelex.urlshortener.responses.UrlResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UrlService {
    private final UrlRepository repository;
    @Value("${local.host}")
    private String address;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public UrlResponse addUrl(ShortUrlRequest requestBody) {
        validateUrl(requestBody.getUrl());
        ShortUrl temp = new ShortUrl(requestBody);
        repository.save(temp);
        String shortUrl = address + temp.getUrl();
        return new UrlResponse(shortUrl, temp.getExpires());
    }

    public void validateUrl(String url) {
        if (url != null) {
            Pattern pattern = Pattern.compile(
                    "^(https?:\\/?\\/?)?([.?\\w\\Q$-_+!*'(),%\\E]+\\.)+(\\w{2,63})(:\\d{1,4})?(\\/[\\w\\Q/$-_+!*'(),%\\E]+)*(\\?[\\w\\Q=$-_+!*'(),%\\E]+)*\\/?$");
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

    public StatisticsResponse getAllUrlStats() {
        Long totalUrls = repository.count();
        Long totalConsumes = repository.sumConsumedTimes();
        return new StatisticsResponse(totalUrls, totalConsumes);
    }

    public StatisticsResponse getUrlConsumptionCount(Long urlId) {
        ShortUrl shortUrl = CheckUrlExists(Math.toIntExact(urlId));
        Long consumes = shortUrl.getConsumedTimes();
        return new StatisticsResponse(consumes);
    }

    private ShortUrl CheckUrlExists(int id) {
        return repository.findById(id)
                         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String getOriginalUrlbyId(Long id) {
        ShortUrl shortUrl = CheckUrlExists(Math.toIntExact(id));
        return shortUrl.getOriginalUrl();
    }

    public void consumeUrl(Long id) {
        ShortUrl shortUrl = CheckUrlExists(Math.toIntExact(id));
        shortUrl.increaseComsumtion();
        repository.save(shortUrl);
    }
}
