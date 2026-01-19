package com.rougesocket.Shortify.Repository;

import com.rougesocket.Shortify.Entity.UrlClickEvent;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlClickEventRepository extends JpaRepository<UrlClickEvent,Long> {

    long countByShortCode(String shortCode);

    @Query("""
            SELECT DATE(e.clickedAt), COUNT(e)
                    FROM UrlClickEvent e
                    WHERE e.shortCode = :shortCode
                    GROUP BY DATE(e.clickedAt)
                    ORDER BY DATE(e.clickedAt)
            """
    )
    List<Object[]> findDailyClicks(@Param("shortcode") String shortCode);
}
