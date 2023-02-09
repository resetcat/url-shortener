package io.codelex.urlshortener.requests;

public class ShortUrlRequest {
    private final String url;
    private Expiration expiration;

    public ShortUrlRequest(String url, Expiration expiration) {
        this.url = url;
        this.expiration = expiration;
    }

    public ShortUrlRequest(String url) {
        this.url = url;
    }

    public Expiration getExpiration() {
        return expiration;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "ShortUrlRequest{" + "url='" + url + '\'' + ", expiration=" + expiration + '}';
    }
}
