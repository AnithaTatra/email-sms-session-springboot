package com.example.crudpractice.repository;

import com.example.crudpractice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //One-to-one mapping jpa native query
    @Query(value = "select * from userinfo u join profiles p  where u.id = p.profile_id",nativeQuery = true)
    List<User> getAllUsersList();

    @Query(value = "select * from userinfo u join profiles p where u.id = :id", nativeQuery = true)
    Optional<User> getUserById(Long id);

    

}
