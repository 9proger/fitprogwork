package ru.omstu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.omstu.dto.CacheKey;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service("mapCache")
public class CacheService implements CacheProvider {
    private static final Logger log = LoggerFactory.getLogger(CacheService.class);

    private final Map<CacheKey, String> cache = new ConcurrentHashMap<>();

    @Override
    public String get(CacheKey key) {
        String value = cache.get(key);
        if (value != null) {
            log.info("[MAP-CACHE] Данные найдены в Map для пути: {}", key.path());
        }
        return value;
    }

    @Override
    public void put(CacheKey key, String value) {
        log.info("[MAP-CACHE] Сохранение в Map для пути: {}", key.path());
        cache.put(key, value);
    }

    @Override
    public void clear() {
        log.info("[MAP-CACHE] Очистка Map-кеша");
        cache.clear();
    }
}
