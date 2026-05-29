package ru.omstu.figprogwork.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CacheRepository extends JpaRepository<CacheEntry, Long> {
    Optional<CacheEntry> findByTypeAndRequestDataAndPath(String type, String data, String path);

    void deleteByCreatedAtBefore(LocalDateTime time);
}
