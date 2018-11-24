package com.oims.futureprogram.service;

import com.oims.futureprogram.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {

    Page<Account> getAccount(Pageable pageable);

    Account getOneAccount(Long accountId);

    Account createAccount(Account account);

    Account updateAccount(Long accountId, Account accountrequest);

    void deleteAccount(Long accountId);
}