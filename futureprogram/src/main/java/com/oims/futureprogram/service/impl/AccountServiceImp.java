package com.oims.futureprogram.service.impl;

import com.oims.futureprogram.exception.ResourceNotFoundException;
import com.oims.futureprogram.model.Account;
import com.oims.futureprogram.repository.AccountRepository;
import com.oims.futureprogram.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Page<Account> getAccount(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public Account getOneAccount(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account not found with Id" + accountId));
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Long accountId, Account accountrequest) {
        return accountRepository.findById(accountId).map(account -> {
            account.setUsername(accountrequest.getUsername());
            account.setPassword(accountrequest.getPassword());
            return accountRepository.save(account);
        }).orElseThrow(() -> new ResourceNotFoundException("Account not found with Id" + accountId));
    }

    @Override
    public void deleteAccount(Long accountId) {
        if (!accountRepository.existsById(accountId)) {
            throw new ResourceNotFoundException("Account not found with Id" + accountId);
        }
        Account account = accountRepository.getOne(accountId);
        accountRepository.delete(account);
    }
}
