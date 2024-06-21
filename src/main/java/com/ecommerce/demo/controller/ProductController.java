package com.ecommerce.demo.controller;

import com.ecommerce.demo.entities.Product;
import com.ecommerce.demo.entities.User;
import com.ecommerce.demo.request.ProductCreateRequest;
import com.ecommerce.demo.request.UserCreateRequest;
import com.ecommerce.demo.response.GenericResponse;
import com.ecommerce.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  ProductService productService;

  @PostMapping("/create")
  public Product createProduct(@RequestBody ProductCreateRequest productCreateRequest){
    return productService.createProduct(productCreateRequest);
  }

  @GetMapping("/getProductByName")
  public ResponseEntity<GenericResponse<List<Product>>> getProductByName (@RequestParam("name") String name){
    List<Product> productList = productService.getProductByName(name);
    GenericResponse<List<Product>> response = new GenericResponse<>(productList,"","success","200");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/getProductById")
  public ResponseEntity<GenericResponse<List<Product>>> getProductById (@RequestParam("id") Integer productId){
    List<Product> productList = productService.getProductById(productId);
    GenericResponse<List<Product>> response = new GenericResponse<>(productList,"","success","200");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
