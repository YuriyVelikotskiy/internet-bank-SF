package ru.study.internetbank.model;

public enum OperationType {
    TAKE_MONEY(1),
    PUT_MONEY(2),
    TRANSFER_IN(3),
    TRANSFER_OUT(4);

    private int value;

    OperationType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}