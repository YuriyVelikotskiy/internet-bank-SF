package ru.study.internetbank.model;

import java.math.BigDecimal;
import java.util.Objects;

public class BalanceResponse {

    private BigDecimal value;
    private String message = "";

    public BalanceResponse(BigDecimal value) {
        this.value = value;
    }

    public BalanceResponse(String message) {
        this.value = ERROR_BALANCE;
        this.message = message;
    }


    public BigDecimal getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return STRING_BALANCE.formatted(value, message);
    }

    private static final BigDecimal ERROR_BALANCE = BigDecimal.valueOf(-1);
    private static final String STRING_BALANCE = "{ value: %s, message: \"%s\" }";
}
