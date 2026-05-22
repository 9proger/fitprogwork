package ru.omstu.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

@Service
public class XmlDataParser implements DataHTMLParser {
    @Override
    public String parse(String data, String path) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().parse(new InputSource(new StringReader(data)));

        String xpath = "/" + path.replace("/[", "[").replaceAll("\\[(\\d+)\\]", "[$1+1]");
        if (!xpath.startsWith("/root")) xpath = "/root" + xpath;

        return XPathFactory.newInstance().newXPath().evaluate(xpath, doc);
    }

    @Override
    public String getType() { return "xml"; }
}
