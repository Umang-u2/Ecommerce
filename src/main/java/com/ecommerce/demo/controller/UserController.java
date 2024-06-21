package com.ecommerce.demo.controller;

import com.ecommerce.demo.entities.User;
import com.ecommerce.demo.request.UserCreateRequest;
import com.ecommerce.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/create")
  public User createUser(@RequestBody UserCreateRequest userCreateRequest){
    return userService.createUser(userCreateRequest);
  }

}
