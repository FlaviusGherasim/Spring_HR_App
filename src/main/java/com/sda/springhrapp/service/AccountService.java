package com.sda.springhrapp.service;

import com.sda.springhrapp.exception.AccountServiceException;
import com.sda.springhrapp.model.Account;
import com.sda.springhrapp.model.Employee;
import com.sda.springhrapp.repository.AccountRepositoryIf;
import com.sda.springhrapp.repository.EmployeeRepositoryIf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepositoryIf accountRepositoryIf;

    @Autowired
    private EmployeeRepositoryIf employeeRepositoryIf;

    public Account saveAccount(Account accountIntroduced) throws AccountServiceException {
        Optional<Employee> employee = employeeRepositoryIf.findById(accountIntroduced.getEmployee().getId());
        if (employee.isPresent()) {
            accountIntroduced.setEmployee(employee.get());
            Account accountSaved= accountRepositoryIf.save(accountIntroduced);
            employee.get().setAccount(accountSaved); // get around this somehow.
            employeeRepositoryIf.save(accountSaved.getEmployee());
            log.info("Account saved successfully.");

//        Account accountSaved;
//        accountSaved = accountRepositoryIf.save(accountIntroduced);
        return accountSaved;

        } else {
            //todo throw new CUSTOM account service exception and create an ExceptionHandler in Controller HOMEWORK
            throw new AccountServiceException("something went wrong with the account. It was not saved.");
        }
    }


    public Integer deleteAccountById(Integer id) {
        Integer account = accountRepositoryIf.deleteAccountById(id);
        if (account != 0) {
            log.info("account with id " + id + " was game ended");
        } else {
            log.warn("account didn't get game ended.");
        }
        return account;
    }

    public List<Account> findAllAccounts() {
//        Iterable<Account> allUsers2 = accountRepositoryIf.findAll();
//        return (List<Account>) allUsers2;

        return accountRepositoryIf.findAll();
    }
}
