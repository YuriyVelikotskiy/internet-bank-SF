package ru.study.internetbank.services;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import ru.study.internetbank.model.*;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class BankService {

    final private BankDao bankDao;

    public BankService(BankDao bankDao) {
        this.bankDao = bankDao;
    }

    public BalanceResponse getBalance(String userId) {
        try {
            return new BalanceResponse(bankDao.getBalance(userId));
        } catch (EmptyResultDataAccessException e) {
            return new BalanceResponse("Пользователь не найден!!!");
        } catch (Exception e) {
            return new BalanceResponse(e.getMessage());
        }
    }

    public OperationListResponse getOperationList(String userId, Date startDate, Date finishDate) {
        if (startDate == null) {
            startDate = minDate;
        }
        if (finishDate == null) {
            finishDate = maxDate;
        }
        try {
            return new OperationListResponse(bankDao.getOperationList(userId, startDate, finishDate));
        } catch (Exception e) {
            return new OperationListResponse(e.getMessage());
        }
    }

    @Transactional
    public TransferMoneyResponse transferMoney(TransferMoneyRequest transferMoneyRequest) {
        try {
            PutRequest takeRequest = new PutRequest(transferMoneyRequest.getValue(), transferMoneyRequest.getUserId());
            takeMoney(takeRequest);
            PutRequest putRequest = new PutRequest(transferMoneyRequest.getValue(), transferMoneyRequest.getPayeeId());
            putMoney(putRequest);
            return new TransferMoneyResponse();
        } catch (Exception e) {
            return  new TransferMoneyResponse(e.getMessage());
        }
    }

    @Transactional
    public PutResponse putMoney(PutRequest putRequest) {
        try {
            BigDecimal currentBalance = getBalance(putRequest.getUserId()).getValue();
            currentBalance = currentBalance.add(putRequest.getValue());
            bankDao.setBalance(putRequest.getUserId(), currentBalance);
            bankDao.setOperation(putRequest.getUserId(), putRequest.getValue(), OperationType.PUT_MONEY.getValue());
            return new PutResponse();
        } catch (Exception e) {
            return new PutResponse(e.getMessage());
        }
    }

    @Transactional
    public PutResponse takeMoney(PutRequest putRequest) {
        try {
            BigDecimal currentBalance = bankDao.getBalance(putRequest.getUserId());
            currentBalance = currentBalance.subtract(putRequest.getValue());
            if (currentBalance.signum() == -1) {
                return new PutResponse("Недостаточно средств");
            }
            bankDao.setBalance(putRequest.getUserId(), currentBalance);
            bankDao.setOperation(putRequest.getUserId(), putRequest.getValue(), OperationType.TAKE_MONEY.getValue());
            return new PutResponse();
        } catch (Exception e) {
            return new PutResponse(e.getMessage());
        }
    }

    static final private Date minDate = new Date(2000, 1, 1);
    static final private Date maxDate = new Date(3000, 1, 1);


}