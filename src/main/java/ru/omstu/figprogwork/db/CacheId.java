package ru.omstu.figprogwork.db;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CacheId implements Serializable {
    private String type;

    @Column(length = 1000)
    private String requestData;

    private String path;

    public CacheId() {}

    public CacheId(String type, String requestData, String path) {
        this.type = type;
        this.requestData = requestData;
        this.path = path;
    }

    public String getType() { return type; }
    public String getRequestData() { return requestData; }
    public String getPath() { return path; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheId cacheId = (CacheId) o;
        return Objects.equals(type, cacheId.type) &&
                Objects.equals(requestData, cacheId.requestData) &&
                Objects.equals(path, cacheId.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, requestData, path);
    }
}