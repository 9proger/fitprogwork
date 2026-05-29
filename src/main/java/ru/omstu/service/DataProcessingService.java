package ru.omstu.service;

import org.springframework.stereotype.Service;

import ru.omstu.dto.CacheKey;
import ru.omstu.service.DataHTMLParser;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DataProcessingService {
    private final Map<String, DataHTMLParser> parsers;
    private final CacheProvider cacheProvider; // Инъекция интерфейса

    public DataProcessingService(List<DataHTMLParser> parserList, CacheProvider cacheProvider) {
        this.cacheProvider = cacheProvider;
        this.parsers = parserList.stream()
                .collect(Collectors.toMap(DataHTMLParser::getType, Function.identity()));
    }

    public String process(String type, String data, String path) throws Exception {
        // Создаем один ключ
        CacheKey key = new CacheKey(type, data, path);

        // Пытаемся достать из кеша (БД или Map подставится автоматически)
        String cachedValue = cacheProvider.get(key);
        if (cachedValue != null) return cachedValue;

        // Если в кеше нет — парсим
        DataHTMLParser parser = parsers.get(type.toLowerCase());
        String result = parser.parse(data, path);

        // Сохраняем в кеш
        cacheProvider.put(key, result);

        return result;
    }
}

