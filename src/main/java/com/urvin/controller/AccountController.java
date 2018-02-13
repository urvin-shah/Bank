package com.urvin.controller;

import com.urvin.domain.Account;
import com.urvin.domain.AccountTransaction;
import com.urvin.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
    public String createAccount(@RequestBody Account accout) {
        boolean success = accountService.createAcccount(accout);
        if(success) {
            return "Account created Successfully!!";
        } else {
            return "Account already exists";
        }
    }

    @GetMapping("/list")
    public List<Account> getAllAccounts() {
        return accountService.listAllAccounts();
    }

    @PostMapping("/{accountId}/transaction/add")
    public String accountTransaction(@PathVariable("accountId") Long accountId, @RequestBody AccountTransaction transaction) {
        if(accountId != null && transaction != null) {
            transaction.setAccountId(accountId);
            return accountService.addTransaction(transaction);
        }
        return "Invalid values";
    }

    @GetMapping("/{accountId}/transaction/list")
    public List<AccountTransaction> getAllTransactions(@PathVariable("accountId") Long accountId) {
        if(accountId != null) {
            return accountService.getTransactionsByAccountId(accountId);
        }
        return null;
    }
}
