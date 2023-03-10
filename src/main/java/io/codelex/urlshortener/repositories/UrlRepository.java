package io.codelex.urlshortener.repositories;

import io.codelex.urlshortener.models.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<ShortUrl, Integer> {
    @Query("SELECT SUM(u.consumedTimes) FROM urls u")
    Long sumConsumedTimes();
}
