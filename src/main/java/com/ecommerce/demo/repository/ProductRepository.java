package com.ecommerce.demo.repository;

import com.ecommerce.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

  List<Product> findByProductName(String productName);

  List<Product> findByProductId(Integer productId);


}
