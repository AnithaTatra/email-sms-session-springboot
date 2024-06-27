package com.example.crudpractice.service;

import com.example.crudpractice.entity.MyView;
import com.example.crudpractice.entity.User;
import com.example.crudpractice.request.UserDto;
import com.example.crudpractice.response.ResponseDto;
import com.example.crudpractice.response.ResponseData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    ResponseDto saveUser(UserDto userDto);

    ResponseData getAllUsers();

    ResponseData getUserById(Long id);

    ResponseDto deleteUser(Long id);

   Page<User> getAllPages(Pageable pageable);

   Page<User> getPagesBySize(int pageNo, int pageSize, String sort);

   List<MyView> getView();

    ResponseData<?> getUsers();

    List<User> showUsersList();

    Optional<User> getUserInfoById(Long id);

}
