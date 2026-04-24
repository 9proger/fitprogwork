package ru.omstu.interfaces;

public class ParserFactory {

    public static DataParser getParser(String fileType) {
        if (fileType.equalsIgnoreCase("json")) {
            return new JsonParser();
        } else if (fileType.equalsIgnoreCase("xml")) {
            return new XmlParser();
        } else {
            throw new IllegalArgumentException("Неподдерживаемый формат: " + fileType);
        }
    }
}
