package ru.study.internetbank.model;

import java.math.BigDecimal;
import java.util.List;

public class OperationListResponse {

    private List<OperationInfo> value;

    private String message = "";

    public OperationListResponse(List<OperationInfo> value) {
        this.value = value;
    }

    public OperationListResponse(String message) {
        this.message = message;
    }

    public List<OperationInfo> getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return STRING_BALANCE.formatted(value, message);
    }

    private static final String STRING_BALANCE = "{ value: %s, message: \"%s\" }";
}
