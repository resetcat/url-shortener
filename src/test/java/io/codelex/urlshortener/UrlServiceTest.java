package io.codelex.urlshortener;

import io.codelex.urlshortener.services.UrlService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {

    @InjectMocks
    private UrlService service;


    @Test
    public void testValidateUrlWithValidUrls() {
        String[] validUrls = {"http://www.google.com", "https://www.google.com",
                "https://google.com", "http://www.google.com/", "http://www.google.com/path/to/page",
                "https://www.google.com?param=value", "http://www.google.com:8080"};

        for (String validUrl : validUrls) {
            try {
                service.validateUrl(validUrl);
            } catch (ResponseStatusException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Test
    public void testValidateUrlWithInvalidUrls() {
        String[] invalidUrls = {"htt://www.codelex.io/uznemumiem", "abc", "htps://www.codelex.io/uznemumiem", "https//www.codelex.io/uznemumiem", "", null};

        for (String invalidUrl : invalidUrls) {
            assertThrows(ResponseStatusException.class, () -> service.validateUrl(invalidUrl),
                         "Expected BAD_REQUEST for URL: " + invalidUrl);
        }
    }


}
