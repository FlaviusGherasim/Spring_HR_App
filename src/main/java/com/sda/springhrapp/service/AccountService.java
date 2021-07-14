package com.sda.springhrapp.service;

import com.sda.springhrapp.model.Account;
import com.sda.springhrapp.repository.AccountRepositoryIf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepositoryIf accountRepositoryIf;

    public Account saveAccount(Account account)
    {
        accountRepositoryIf.save(account);
        log.info("Account saved succesfully.");
        return account;
    }

    public Integer deleteAccountById( Integer id)
    {
        Integer account= accountRepositoryIf.deleteAccountById(id);
        if (account != 0) {
            log.info("account with id " + id + " was game ended");
        } else {
            log.warn("account didn't get game ended.");
        }
        return account;
    }
    public List<Account> findAllAccounts()
    {
//        Iterable<Account> allUsers2 = accountRepositoryIf.findAll();
//        return (List<Account>) allUsers2;

        return accountRepositoryIf.findAll();
    }
}
