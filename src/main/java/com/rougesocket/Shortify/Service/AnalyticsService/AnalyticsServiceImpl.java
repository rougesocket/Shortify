package com.rougesocket.Shortify.Service.AnalyticsService;

import com.rougesocket.Shortify.Entity.UrlClickEvent;
import com.rougesocket.Shortify.Repository.UrlClickEventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsServiceImpl implements AnalyticsService{


    private final UrlClickEventRepository clickEventRepository;

    public AnalyticsServiceImpl(UrlClickEventRepository urlClickEventRepository){
        this.clickEventRepository= urlClickEventRepository;
    }

    @Override
    public void recordClick(String shortCode, String ipAddress, String userAgent, String referrer) {
        UrlClickEvent urlClickEvent = new UrlClickEvent(shortCode, LocalDate.now(),ipAddress,userAgent,referrer);

        clickEventRepository.save(urlClickEvent);
    }

    @Override
    public long getTotalClicks(String shortCode) {
        return clickEventRepository.countByShortCode(shortCode);
    }

    @Override
    public Map<LocalDate, Long> getDailyClicks(String shortCode) {
        List<Object[]> rows = clickEventRepository.findDailyClicks(shortCode);

        Map<LocalDate,Long > result = new LinkedHashMap<>();
        for(Object[] row : rows){
            LocalDate date = ((java.sql.Date)row[0]).toLocalDate();
            result.put(date,(Long)row[1]);
        }
        return result;
    }
}
