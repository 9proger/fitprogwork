package ru.omstu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.omstu.dto.CacheKey;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {
    private static final Logger log = LoggerFactory.getLogger(CacheService.class);

    private final Map<CacheKey, String> cache = new ConcurrentHashMap<>();

    public String get(CacheKey key) {
        String value = cache.get(key);
        if (value != null) {
            log.info("[CACHE] Данные найдены в кеше для пути: {}", key.path());
        }
        return value;
    }

    public void put(CacheKey key, String value) {
        log.info("[CACHE] Сохранение результата в кеш для пути: {}", key.path());
        cache.put(key, value);
    }

    public void clear() {
        cache.clear();
    }
}
