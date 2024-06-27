package com.example.crudpractice.controller;

import com.example.crudpractice.entity.MyView;
import com.example.crudpractice.entity.User;
import com.example.crudpractice.request.UserDto;
import com.example.crudpractice.response.ResponseDto;
import com.example.crudpractice.response.ResponseData;
import com.example.crudpractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userApi/v1")
public class UserController {

    @Autowired
    UserService userService;

   @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto saveUser(@RequestBody UserDto userDto) {
        ResponseDto responseEntity = userService.saveUser(userDto);
        return responseEntity;
    }

    @GetMapping("/getUsers")
    public ResponseData getListOfUsers() {
        ResponseData list = userService.getAllUsers();
        return list;
    }

    @GetMapping("/getUser")
    public ResponseData getUser(@RequestParam("id") Long id) {
       ResponseData user = userService.getUserById(id);
        return  user;
    }

    @DeleteMapping("/deleteUser")
    public ResponseDto deleteUserById(@RequestParam("id") Long id) {
        ResponseDto response = userService.deleteUser(id);
        return  response;
    }

    @GetMapping("/pagination")
    public Page<User> showPages(Pageable pageable) {
        Page<User> userPages = userService.getAllPages(pageable);
        return userPages;
    }

    @GetMapping("/getPages")
    public Page<User> pageRequest(@RequestParam("no") int pageNo, @RequestParam("size") int pageSize, @RequestParam("sort") String sort) {
        Page<User> request = userService.getPagesBySize(pageNo,pageSize,sort);
        return request;
    }

    @GetMapping("/view")
   public List<MyView> createView() {
        List<MyView> myViewList = userService.getView();
        return  myViewList;
  }

  @GetMapping("/listOfUsers")
  public ResponseData<?> getUsersList() {
      ResponseData<?> response = userService.getUsers();
        return response;
  }

  @GetMapping("/show")
  public List<User> findAll() {
        List<User> response = userService.showUsersList();
        return response;
  }

  @GetMapping("/get")
  public Optional<User> getUserById(@RequestParam Long id) {
        Optional<User> user = userService.getUserInfoById(id);
        return user;
  }

@GetMapping("/third-party-api")
   public String getCountriesList() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept",MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<?> entity = new HttpEntity<>(headers);
            String url = "https://countriesnow.space/api/v0.1/countries/population/cities";
            ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET,entity, String.class);
            HttpStatusCode code = res.getStatusCode();
            if (code == HttpStatus.OK)
                return res.getBody();
        }catch (RestClientException e) {
            throw new RuntimeException();
        }
       return null;
   }
}
