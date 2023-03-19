package ru.study.internetbank.model;

public class TransferMoneyResponse {
    private static final String STRING_BALANCE = "{ value: %s, message: \"%s\" }";
    private static final int NEGATIVE_VALUE = 0;
    private static final int POSITIVE_VALUE = 1;
    private int value = NEGATIVE_VALUE;
    private String message = "";

    public TransferMoneyResponse() {
        this.value = POSITIVE_VALUE;
    }

    public TransferMoneyResponse(String message) {
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