package com.ecommerce.demo.service;

import com.ecommerce.demo.entities.Address;
import com.ecommerce.demo.entities.User;
import com.ecommerce.demo.repository.AddressRepository;
import com.ecommerce.demo.repository.UserRepository;
import com.ecommerce.demo.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  @Autowired
  UserRepository userRepo;

  @Autowired
  AddressRepository addressRepo;

  public User createUser(UserCreateRequest userCreateRequest){

    Address addressDB = addressRepo.save(userCreateRequest.toAddress());
    User user = userCreateRequest.toUser();
    user.setAddress(addressDB);
    return userRepo.save(user);
  }

  public List<User> getUser(Integer userId){
    return userRepo.findByUserId(userId);
  }
}
