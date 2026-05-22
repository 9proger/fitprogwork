package ru.omstu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResponse {
    private String value;
    private String error;

    public DataResponse(String value, String error) {
        this.value = value;
        this.error = error;
    }

    public String getValue() { return value; }
    public String getError() { return error; }
}
