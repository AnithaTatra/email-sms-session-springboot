package com.example.crudpractice.controller;

import com.example.crudpractice.entity.Account;
import com.example.crudpractice.response.ResponseData;
import com.example.crudpractice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accountApi/v4")
public class AccountController {

    @Autowired
    AccountService accountService;

    //JPQL Query
    @GetMapping("/account")
    public Optional<Account> findAccountById(@RequestParam Long id) {
        return accountService.findAccount(id);
    }

    //JPQL List Query
    @GetMapping("/getList")
    public ResponseData findAccountsData() {
        ResponseData response = accountService.findAccounts();
        return response;
    }

    //CriteriaBuilder List Query
    @GetMapping("/criteriaList")
    public List<Account> getAccounts() {
        List<Account> response = accountService.listOfAccounts();
        return  response;
    }
}
