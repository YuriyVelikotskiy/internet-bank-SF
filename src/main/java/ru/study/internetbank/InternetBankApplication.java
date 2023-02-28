package ru.study.internetbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.study.internetbank.model.BalanceResponse;
import ru.study.internetbank.services.BankService;

@SpringBootApplication
public class InternetBankApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(InternetBankApplication.class, args);

		BankService bankService = context.getBean(BankService.class);

		BalanceResponse response = bankService.getBalance("test");
		System.out.println("success balance: " + response.toString());

		response = bankService.getBalance("none");
		System.out.println("error balance: " + response.toString());
	}
}
