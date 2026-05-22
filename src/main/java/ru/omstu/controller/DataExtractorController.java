package ru.omstu.controller;

import org.springframework.web.bind.annotation.*;
import ru.omstu.dto.DataRequest;
import ru.omstu.dto.DataResponse;
import ru.omstu.service.DataProcessingService;

@RestController
@RequestMapping("/api/data")
public class DataExtractorController {
    private final DataProcessingService service;

    public DataExtractorController(DataProcessingService service) {
        this.service = service;
    }

    @PostMapping("/extract")
    public DataResponse extract(@RequestBody DataRequest request) {
        try {
            String val = service.process(request.getType(), request.getData(), request.getPath());
            return new DataResponse(val, null);
        } catch (Exception e) {
            return new DataResponse(null, e.getMessage());
        }
    }
}
/*
{
  "type": "json",
  "data": "{ \"user\": { \"name\": \"Alex\" } }",
  "path": "user/name"
}

{
  "type": "yaml",
  "data": "{ \"user\": { \"name\": \"Alex\" } }",
  "path": "user/name"
}

{
  "type": "xml",
  "data": "<user><name>Alex</name></user>",
  "path": "user/name"
}
 */