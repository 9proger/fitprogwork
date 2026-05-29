package ru.omstu.figprogwork.db;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cache_storage")
public class CacheEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    //@Lob // Для больших строк данных
    private String requestData;
    private String path;
    private String resultValue;

    private LocalDateTime createdAt;

    public CacheEntry() {}

    public CacheEntry(String type, String data, String path, String result) {
        this.type = type;
        this.requestData = data;
        this.path = path;
        this.resultValue = result;
        this.createdAt = LocalDateTime.now();
    }
    public String getResultValue() { return resultValue; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
