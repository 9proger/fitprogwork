package ru.omstu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.omstu.dto.CacheKey;
import ru.omstu.figprogwork.db.CacheEntry;
import ru.omstu.figprogwork.db.CacheRepository;

@Service
@Primary
public class DbCacheProvider implements CacheProvider {
    private static final Logger log = LoggerFactory.getLogger(DbCacheProvider.class);
    private final CacheRepository repository;

    public DbCacheProvider(CacheRepository repository) {
        this.repository = repository;
    }

    @Override
    public String get(CacheKey key) {
        return repository.findByTypeAndRequestDataAndPath(key.type(), key.data(), key.path())
                .map(entry -> {
                    log.info("[DB-CACHE] Найдено в БД для: {}", key.path());
                    return entry.getResultValue();
                })
                .orElse(null);
    }

    @Override
    public void put(CacheKey key, String value) {
        log.info("[DB-CACHE] Сохранение в БД для: {}", key.path());
        repository.save(new CacheEntry(key.type(), key.data(), key.path(), value));
    }

    @Override
    public void clear() {
        repository.deleteAll();
    }
}
