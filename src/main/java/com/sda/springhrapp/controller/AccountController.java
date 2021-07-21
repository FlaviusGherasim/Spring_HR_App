package com.sda.springhrapp.controller;

import com.sda.springhrapp.exception.AccountServiceException;
import com.sda.springhrapp.model.Account;
import com.sda.springhrapp.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/humanresources/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<String> findAllAccounts()
    {
        List<Account> accountList= accountService.findAllAccounts();
        log.info("Accounts found.");
        log.debug(accountList.toString());
        return new ResponseEntity<>(accountList.toString(), HttpStatus.OK);
    }

    @PostMapping("/accounts")
    public ResponseEntity<String> createAccount(@RequestBody Account account) throws AccountServiceException {
        accountService.saveAccount(account);
        log.info(account.toString());
        return new ResponseEntity<>(account.toString(), HttpStatus.CREATED);
    }

    @DeleteMapping("/accounts")
    @Transactional
    public ResponseEntity<String> deleteAccount(@RequestParam(value= "id") Integer id)
    {
        Integer result;
        if(id==null)
        {
            throw new IllegalArgumentException("id can't be null");
        }
        else
        {
            result=accountService.deleteAccountById(id);
            if(result==0)
            {
                log.info("Account did not get deleted.");
                return new ResponseEntity<>("Can't delete what doesn't exist.", HttpStatus.I_AM_A_TEAPOT);
            }
            else {
                log.info("account has been removed.");
                return new ResponseEntity<>("Account with id "+ id +" has been removed.", HttpStatus.OK);
            }
        }

    }

    @PutMapping("/accounts")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) throws AccountServiceException {
        Account updatedAccount= accountService.saveAccount(account);
        return ResponseEntity.ok(updatedAccount);
    }

}
