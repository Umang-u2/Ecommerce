package com.ecommerce.demo.service;

import com.ecommerce.demo.entities.Product;
import com.ecommerce.demo.entities.ProductInventory;
import com.ecommerce.demo.repository.ProductRepository;
import com.ecommerce.demo.request.ProductCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

  @Autowired
  ProductRepository productRepo;

  public Product createProduct(ProductCreateRequest productCreateRequest){
    return productRepo.save(productCreateRequest.toProduct());
  }

  public List<Product> getProductByName(String name){
    return productRepo.findByProductName(name);
  }

  public List<Product> getProductById(Integer productId){
    return productRepo.findByProductId(productId);
  }

}
