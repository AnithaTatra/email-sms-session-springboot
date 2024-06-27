package com.example.crudpractice.repository;

import com.example.crudpractice.entity.MyView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyViewRepository extends JpaRepository<MyView,Long> {
}
