package ru.omstu.figprogwork.db;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cache_storage")
public class CacheEntry {

    @EmbeddedId
    private CacheId id;

    @Column(columnDefinition = "CLOB")
    private String resultValue;

    private LocalDateTime createdAt;

    public CacheEntry() {}

    public CacheEntry(CacheId id, String resultValue) {
        this.id = id;
        this.resultValue = resultValue;
        this.createdAt = LocalDateTime.now();
    }

    public CacheId getId() { return id; }
    public String getResultValue() { return resultValue; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}