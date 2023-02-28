package ru.study.internetbank.controllers;

import org.springframework.web.bind.annotation.*;
import ru.study.internetbank.model.*;
import ru.study.internetbank.services.BankService;

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

    @PostMapping("/put-money/")
    public PutResponse putMoney(@RequestBody PutRequest putRequest){
        return bankService.putMoney(putRequest.getUserId(), putRequest.getValue());
    }

    @PostMapping("/take-money/")
    public PutResponse takeMoney(@RequestBody PutRequest putRequest){
        return bankService.takeMoney(putRequest.getUserId(), putRequest.getValue());
    }
}
