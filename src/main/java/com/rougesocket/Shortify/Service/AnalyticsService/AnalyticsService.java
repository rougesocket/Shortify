package com.rougesocket.Shortify.Service.AnalyticsService;

import java.time.LocalDate;
import java.util.Map;

public interface AnalyticsService {

    void recordClick(
            String shortCode,
            String ipAddress,
            String userAgent,
            String referrer
    );

    long getTotalClicks(String shortCode);

    Map<LocalDate, Long> getDailyClicks(String shortCode);
}
