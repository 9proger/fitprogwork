package ru.omstu.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.stereotype.Service;

@Service
public class YamlDataParser extends JsonDataParser {
    private final ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());

    @Override
    public String parse(String data, String path) throws Exception {
        JsonNode node = yamlMapper.readTree(data);
        return findValue(node, path); // Используем метод из родителя
    }

    @Override
    public String getType() { return "yaml"; }
}
