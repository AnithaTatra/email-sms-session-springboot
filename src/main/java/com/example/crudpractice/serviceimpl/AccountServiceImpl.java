package com.example.crudpractice.serviceimpl;

import com.example.crudpractice.constants.ConstantStatus;
import com.example.crudpractice.entity.Account;
import com.example.crudpractice.repository.AccountRepository;
import com.example.crudpractice.request.AccountDto;
import com.example.crudpractice.response.ResponseData;
import com.example.crudpractice.service.AccountService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    AccountRepository accountInterface;
    @Override
    public Optional<Account> findAccount(Long id) {
        try {
            Optional<Account>  list = accountInterface.findAccount(id);
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseData findAccounts() {
        ResponseData data = new ResponseData();
        List<Account> accounts = new ArrayList<>();
        List<Account> accountList = accountInterface.findAllAccounts();
        AccountDto accountDto = new AccountDto();
        for (Account acc : accountList) {
            accountDto.setAccountNo(acc.getAccountNumber());
            accountDto.setName(acc.getFullName());
            accountDto.setBalance(acc.getBalance());
            accounts.add(acc);
        }
        data.setData(accounts);
        data.setStatus(ConstantStatus.SUCCESS);

        return data;
    }

    @Override
    public List<Account> listOfAccounts() {

        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(Account.class);
            Root<Account> root = query.from(Account.class);
            query.select(root);
            TypedQuery type = entityManager.createQuery(query);
            return type.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
