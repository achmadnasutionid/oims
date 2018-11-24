package com.oims.futureprogram.controller;

import com.oims.futureprogram.model.Account;
import com.oims.futureprogram.repository.AccountRepository;
import com.oims.futureprogram.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/account")
    public Page<Account> getAccount(Pageable pageable) {
        return accountService.getAccount(pageable);
    }

    @GetMapping("/account/{accountId}")
    public Account getOneAccount(@PathVariable Long accountId) {
        return accountService.getOneAccount(accountId);
    }

    @PostMapping("/account")
    public Account createAccount(@Valid @RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PutMapping("/account/{accountId}")
    public Account updateAccount(@PathVariable Long accountId, @Valid @RequestBody Account accountrequest) {
        return accountService.updateAccount(accountId, accountrequest);
    }

    @DeleteMapping("/account/{accountId}")
    public void deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
    }
}
