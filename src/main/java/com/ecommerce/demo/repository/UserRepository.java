package com.ecommerce.demo.repository;

import com.ecommerce.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

  List<User> findByUserId(Integer id);

}
