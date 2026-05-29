package ru.omstu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.omstu.figprogwork.db.CacheRepository;
import java.time.LocalDateTime;

@Component
public class CacheScheduler {

    @Autowired
    private CacheRepository repository;

    @Scheduled(fixedRate = 30000)
    @Transactional
    public void cleanOldCache() {
        try {
            LocalDateTime limit = LocalDateTime.now().minusSeconds(60);
            repository.deleteByCreatedAtBefore(limit);
            System.out.println("Планировщик: Старый кеш очищен.");
        } catch (Exception e) {
            System.err.println("Ошибка планировщика: " + e.getMessage());
        }
    }

    @Scheduled(cron = "0 0 0 * * SUN")
    @Transactional
    public void clearWeekly() {
        repository.deleteAll();
        System.out.println("Планировщик: Еженедельная полная очистка выполнена.");
    }
}
