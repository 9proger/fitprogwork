package ru.omstu.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class JsonDataParser implements DataHTMLParser {
    protected final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String parse(String data, String path) throws Exception {
        JsonNode node = mapper.readTree(data);
        return findValue(node, path);
    }

    protected String findValue(JsonNode node, String path) {
        String[] parts = path.split("/");
        for (String part : parts) {
            if (part.isEmpty()) continue;
            if (part.startsWith("[") && part.endsWith("]")) {
                int index = Integer.parseInt(part.substring(1, part.length() - 1));
                node = node.get(index);
            } else {
                node = node.get(part);
            }
            if (node == null) return "Path not found";
        }
        return node.asText();
    }

    @Override
    public String getType() { return "json"; }
}
