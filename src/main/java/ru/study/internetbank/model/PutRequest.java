package ru.study.internetbank.model;

import java.math.BigDecimal;

public class PutRequest {
    private BigDecimal value;
    private String userId;

    public PutRequest(BigDecimal value, String userId) {
        this.value = value;
        this.userId = userId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
