package ru.omstu.service;

public interface DataHTMLParser {
    String parse(String data, String path) throws Exception;
    String getType();
}
