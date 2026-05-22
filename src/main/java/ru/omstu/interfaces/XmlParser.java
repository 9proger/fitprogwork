package ru.omstu.interfaces;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlParser implements DataParser {

    @Override
    public String parse(String filePath, String xmlPath) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (is == null) return "Файл не найден";

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            XPath xPath = XPathFactory.newInstance().newXPath();

            String correctedPath = xmlPath.replace("/[", "[");

            Pattern p = Pattern.compile("\\[(\\d+)\\]");
            Matcher m = p.matcher(correctedPath);
            StringBuilder sb = new StringBuilder();
            while (m.find()) {
                int index = Integer.parseInt(m.group(1)) + 1; // [1] становится [2]
                m.appendReplacement(sb, "[" + index + "]");
            }
            m.appendTail(sb);

            String finalPath = sb.toString();
            if (!finalPath.startsWith("/root")) {
                finalPath = "/root" + finalPath;
            }

            String result = xPath.evaluate(finalPath, doc);

            return (result == null || result.isEmpty()) ? "Путь не найден" : result;

        } catch (Exception e) {
            return "Ошибка XML: " + e.getMessage();
        }
    }
}
