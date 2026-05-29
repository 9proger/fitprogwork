package ru.omstu.service;

import ru.omstu.dto.CacheKey;

public interface CacheProvider {
    String get(CacheKey key);

    void put(CacheKey key, String value);

    void clear();
}