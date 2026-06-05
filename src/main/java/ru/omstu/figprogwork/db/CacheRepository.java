package ru.omstu.figprogwork.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface CacheRepository extends JpaRepository<CacheEntry, CacheId> {

    void deleteByCreatedAtBefore(LocalDateTime time);
}