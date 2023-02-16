package io.codelex.urlshortener.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.codelex.urlshortener.requests.Expiration;
import io.codelex.urlshortener.requests.ShortUrlRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity(name = "urls")
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long url;
    private String originalUrl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime expires;
    private Long consumedTimes = 0L;

    public ShortUrl(ShortUrlRequest request) {
        originalUrl = request.getUrl();
        if (request.getExpiration() != null) {
            expires = setExpirationFromRequest(request.getExpiration());
        }
    }

    private LocalDateTime setExpirationFromRequest(Expiration expire) {
        String unit = expire.getUnit();
        int amount = expire.getAmount();
        if (unit.equals("days")) {
            return LocalDateTime.now().plusDays(amount);
        } else if (unit.equals("hours")) {
            return LocalDateTime.now().plusHours(amount);
        } else {
            throw new IllegalArgumentException("Invalid unit: " + unit);
        }
    }

    public ShortUrl() {
    }

    public Long getUrl() {
        return url;
    }

    public Long getConsumedTimes() {
        return consumedTimes;
    }

    public void setConsumedTimes(Long consumedTimes) {
        this.consumedTimes = consumedTimes;
    }

    public void increaseConsumption() {
        setConsumedTimes(getConsumedTimes() + 1);
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public LocalDateTime getExpires() {
        return expires;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShortUrl shortUrl = (ShortUrl) o;
        return Objects.equals(url, shortUrl.url) &&
                Objects.equals(originalUrl, shortUrl.originalUrl) &&
                Objects.equals(expires, shortUrl.expires) &&
                Objects.equals(consumedTimes, shortUrl.consumedTimes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, originalUrl, expires, consumedTimes);
    }
}
