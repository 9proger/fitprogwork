package ru.omstu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.omstu.dto.CacheKey;
import ru.omstu.figprogwork.db.CacheEntry;
import ru.omstu.figprogwork.db.CacheId;
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
        CacheId cid = new CacheId(key.type(), key.data(), key.path());

        return repository.findById(cid)
                .map(entry -> {
                    log.info("[DB-CACHE] Найдено по ключу для: {}", key.path());
                    return entry.getResultValue();
                })
                .orElse(null);
    }

    @Override
    public void put(CacheKey key, String value) {
        log.info("[DB-CACHE] Сохранение в БД: {}", key.path());
        CacheId cid = new CacheId(key.type(), key.data(), key.path());
        repository.save(new CacheEntry(cid, value));
    }

    @Override
    public void clear() {
        repository.deleteAll();
    }
}