package ru.study.internetbank.model;

public class PutResponse {
    private static final String STRING_BALANCE = "{ value: %s, message: \"%s\" }";
    private static final int NEGATIVE_VALUE = 0;
    private static final int POSITIVE_VALUE = 1;
    private int value = NEGATIVE_VALUE;
    private String message = "";

    public PutResponse() {
        this.value = POSITIVE_VALUE;
    }

    public PutResponse(String message) {
        this.message = message;
    }


    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return STRING_BALANCE.formatted(value, message);
    }
}
