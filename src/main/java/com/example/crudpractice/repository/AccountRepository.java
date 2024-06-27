package com.example.crudpractice.repository;

import com.example.crudpractice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

   @Query("select a from Account a where a.id = :id")
   Optional<Account> findAccount(@Param("id") Long id);

   @Query("select a from Account a")
   List<Account> findAllAccounts();

   
}
