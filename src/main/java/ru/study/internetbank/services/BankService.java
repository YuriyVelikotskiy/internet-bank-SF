package ru.study.internetbank.services;

import org.springframework.stereotype.Service;
import ru.study.internetbank.model.BalanceResponse;
import ru.study.internetbank.model.PutResponse;

import java.math.BigDecimal;

@Service
public class BankService {

    final private BankDao bankDao;

    public BankService(BankDao bankDao) {
        this.bankDao = bankDao;
    }

    public BalanceResponse getBalance(String userId) {
        try {
            return new BalanceResponse(bankDao.getBalance(userId));
        } catch (Exception e) {
            return new BalanceResponse(e.getMessage());
        }
    }

    public PutResponse putMoney(String userId, BigDecimal amount) {
        try {
            BigDecimal currentBalance = bankDao.getBalance(userId);
            currentBalance = currentBalance.add(amount);
            bankDao.setBalance(userId, currentBalance);
            return new PutResponse();
        } catch (Exception e) {
            return new PutResponse(e.getMessage());
        }
    }
    public PutResponse takeMoney(String userId, BigDecimal amount) {
        try {
            BigDecimal currentBalance = bankDao.getBalance(userId);
            currentBalance = currentBalance.subtract(amount);
            if(currentBalance.signum() == -1) {
                return new PutResponse("Недостаточно средств");
            }
            bankDao.setBalance(userId, currentBalance);
            return new PutResponse();
        } catch (Exception e) {
            return new PutResponse(e.getMessage());
        }
    }
}

