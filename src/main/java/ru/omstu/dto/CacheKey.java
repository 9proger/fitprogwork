package ru.omstu.dto;

public record CacheKey(String type, String data, String path) {
    // Record автоматически реализует equals() и hashCode(),
    // что идеально для использования в качестве ключа в Map
}
