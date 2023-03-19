package ru.study.internetbank.model;

import java.math.BigDecimal;

public class TransferMoneyRequest {
    private BigDecimal value;
    private String userId;
    private String payeeId;

    public TransferMoneyRequest(BigDecimal value, String userId, String payeeId) {
        this.value = value;
        this.userId = userId;
        this.payeeId = payeeId;
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

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }
}