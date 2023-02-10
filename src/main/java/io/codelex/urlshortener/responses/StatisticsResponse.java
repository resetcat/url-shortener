package io.codelex.urlshortener.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

public class StatisticsResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long timesConsumed;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long totalShortenedUrlCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long shortenedUrlsConsumedCount;

    public StatisticsResponse(Long timesConsumedSingle) {
        this.timesConsumed = timesConsumedSingle;
    }

    public StatisticsResponse(Long totalShortenedUrlCount, Long shortenedUrlsConsumedCount) {
        this.totalShortenedUrlCount = totalShortenedUrlCount;
        this.shortenedUrlsConsumedCount = shortenedUrlsConsumedCount;
    }
    public Long getTimesConsumed() {
        return timesConsumed;
    }

    public Long getTotalShortenedUrlCount() {
        return totalShortenedUrlCount;
    }

    public Long getShortenedUrlsConsumedCount() {
        return shortenedUrlsConsumedCount;
    }
}
