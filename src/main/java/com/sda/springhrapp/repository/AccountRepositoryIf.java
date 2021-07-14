package com.sda.springhrapp.repository;

import com.sda.springhrapp.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepositoryIf extends CrudRepository<Account, Integer> {
    Integer deleteAccountById(Integer id);
    List<Account> findAll();
}
