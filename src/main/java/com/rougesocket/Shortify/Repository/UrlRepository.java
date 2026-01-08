package com.rougesocket.Shortify.Repository;

import com.rougesocket.Shortify.Entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url,Long> {

    Optional<Url> findByShortCode(String shortCode);

}
