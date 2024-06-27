package com.example.crudpractice.service;

import com.example.crudpractice.entity.Account;
import com.example.crudpractice.response.ResponseData;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Optional<Account> findAccount(Long id);

    ResponseData findAccounts();

    List<Account> listOfAccounts();
}
