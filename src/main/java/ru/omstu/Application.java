package ru.omstu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
//http://localhost:8080/api/data/extract

/*
{
  "type": "xml",
  "data": "<root><user><name>Alex</name></user></root>",
  "path": "user/name"
}
 */