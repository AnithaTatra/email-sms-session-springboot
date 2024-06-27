package com.example.crudpractice.serviceimpl;

import com.example.crudpractice.constants.ConstantStatus;
import com.example.crudpractice.entity.MyView;
import com.example.crudpractice.entity.User;
import com.example.crudpractice.repository.MyViewRepository;
import com.example.crudpractice.repository.UserRepository;
import com.example.crudpractice.request.MyViewDto;
import com.example.crudpractice.request.UserDto;
import com.example.crudpractice.response.ResponseDto;
import com.example.crudpractice.response.ResponseData;
import com.example.crudpractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MyViewRepository viewRepository;

    @Override
    public ResponseDto saveUser(UserDto userDto) {
        ResponseDto response = new ResponseDto();


        try {
            if (ConstantStatus.FLAG.equals(userDto.getFlag())) {

                User user = new User();
                user.setName(userDto.getName());
                user.setPassword(userDto.getPassword());
                user.setAge(userDto.getAge());
                user.setEmail(userDto.getEmail());
                userRepository.save(user);
                response.setMessage(ConstantStatus.SUCCESS);

            } else {

                User user = userRepository.findById(userDto.getId()).get();
                    user.setName(userDto.getName());
                    user.setPassword(userDto.getPassword());
                    user.setAge(userDto.getAge());
                    user.setEmail(userDto.getEmail());
                userRepository.save(user);
                response.setMessage(ConstantStatus.SUCCESS);
                }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public ResponseData getAllUsers() {
        UserDto dto = null;

        List<User> listData = new ArrayList<>();
        List<User>userList = userRepository.findAll();
        ResponseData responseData = new ResponseData();

        if(userList != null && !userList.isEmpty()) {
        for(User response : userList ) {
            dto = new UserDto();
            dto.setName(response.getName());
            dto.setPassword(response.getPassword());
            dto.setAge(response.getAge());
            dto.setEmail(response.getEmail());
            listData.add(response);
          }
        } else {
            throw new RuntimeException("Data Not Found");
        }

        responseData.setData(listData);
        responseData.setStatus("Ok");


        return responseData;
    }

    @Override
    public ResponseData getUserById(Long id) {
     Optional<User> optionalUser = null;
     ResponseData response = new ResponseData();
        try {
           optionalUser = userRepository.findById(id);
           response.setData(optionalUser);
           response.setStatus("Ok");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public ResponseDto deleteUser(Long id) {

        ResponseDto response = new ResponseDto();

        try {
            userRepository.deleteById(id);
            response.setMessage("Successfully Deleted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return response;
    }

    @Override
    public Page<User> getAllPages(Pageable pageable) {
        try {
            Page<User> pages = userRepository.findAll(pageable);
            return pages;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Page<User> getPagesBySize(int pageNo, int pageSize, String sort) {
        try {
            Pageable userPage  = PageRequest.of(pageNo,pageSize,Sort.by(sort).descending());

            return  userRepository.findAll(userPage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<MyView> getView() {
        MyViewDto dto = new MyViewDto();
        List<MyView> viewList = new ArrayList<>();
        List<MyView> views = viewRepository.findAll();

        if( views != null && !views.isEmpty()) {
            for (MyView view : views) {
                dto.setId(view.getId());
                dto.setName(view.getName());
                dto.setPassword(view.getPassword());
                viewList.add(view);
            }
        } else {
            throw new RuntimeException("Data Not Found");
        }
        return viewList;
    }

    @Override
    public ResponseData<?> getUsers() {
        ResponseData data = new ResponseData();
        List<User> users = null;
        UserDto dto = new UserDto();
        List<User> userList = new ArrayList<>();
        users = userRepository.findAll();
        for(User user : users) {
            dto.setName(user.getName());
            dto.setAge(user.getAge());
            dto.setEmail(user.getEmail());
            dto.setPassword(user.getPassword());
            userList.add(user);
        }
        data.setData(userList);
        data.setStatus("Ok");

        return data;
    }

    @Override
    public List<User> showUsersList() {
        List<User> list = userRepository.getAllUsersList();

        return list;
    }

    @Override
    public Optional<User> getUserInfoById(Long id) {
        Optional<User> optionalUser = userRepository.getUserById(id);
        return optionalUser;
    }


}
