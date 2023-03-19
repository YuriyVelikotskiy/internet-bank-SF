package ru.study.internetbank.model;

import java.math.BigDecimal;
import java.util.Date;

public class OperationInfo {
    Date date;
    String type;
    BigDecimal amount;

    public OperationInfo(Date date, String type, BigDecimal amount) {
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
