package ru.omstu.service;

import org.springframework.stereotype.Service;

import ru.omstu.dto.CacheKey;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DataProcessingService {
    private final Map<String, DataHTMLParser> parsers;
    private final CacheService cacheService;

    public DataProcessingService(List<DataHTMLParser> parserList, CacheService cacheService) {
        this.cacheService = cacheService;
        this.parsers = parserList.stream()
                .collect(Collectors.toMap(DataHTMLParser::getType, Function.identity()));
    }

    public String process(String type, String data, String path) throws Exception {
        CacheKey key = new CacheKey(type, data, path);


        String cachedValue = cacheService.get(key);
        if (cachedValue != null) {
            return cachedValue;
        }

        DataHTMLParser parser = parsers.get(type.toLowerCase());
        if (parser == null) throw new IllegalArgumentException("Unknown type");

        String result = parser.parse(data, path);

        cacheService.put(key, result);

        return result;
    }
}

