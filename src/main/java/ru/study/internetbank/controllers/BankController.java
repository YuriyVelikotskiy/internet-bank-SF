package ru.study.internetbank.controllers;

import org.springframework.web.bind.annotation.*;
import ru.study.internetbank.model.*;
import ru.study.internetbank.services.BankService;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/balance/{userId}")
    public BalanceResponse getBalance(@PathVariable String userId) {
        return bankService.getBalance(userId);
    }

    @GetMapping("/operations?user_id={userId}&start_date={startDate}&finish_date={finishDate}")
    public OperationListResponse getOperationList(@RequestParam String userId, @RequestParam Date startDate, @RequestParam Date finishDate) {
        return bankService.getOperationList(userId, startDate, finishDate);
    }

    @PostMapping("/transfer-money")
    public TransferMoneyResponse transferMoney(@RequestBody TransferMoneyRequest transferMoneyRequest) {
        return bankService.transferMoney(transferMoneyRequest);
    }

    @PostMapping("/put-money")
    public PutResponse putMoney(@RequestBody PutRequest putRequest) {
        return bankService.putMoney(putRequest);
    }

    @PostMapping("/take-money")
    public PutResponse takeMoney(@RequestBody PutRequest putRequest) {
        return bankService.takeMoney(putRequest);
    }
}