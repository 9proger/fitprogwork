package ru.omstu.interfaces;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;

public class JsonParser implements DataParser {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String parse(String filePath, String jsonPath) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (is == null) return "Файл не найден";

            JsonNode node = mapper.readTree(is);
            for (String part : jsonPath.split("/")) {
                if (part.isEmpty()) continue;

                if (part.startsWith("[") && part.endsWith("]")) {
                    int index = Integer.parseInt(part.substring(1, part.length() - 1));
                    node = node.get(index);
                } else {
                    node = node.get(part);
                }
                if (node == null) return "Путь не найден";
            }
            return node.asText();
        } catch (Exception e) {
            return "Ошибка JSON: " + e.getMessage();
        }
    }
}
