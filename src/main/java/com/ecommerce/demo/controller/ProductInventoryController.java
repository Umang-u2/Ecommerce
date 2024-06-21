package com.ecommerce.demo.controller;

import com.ecommerce.demo.entities.OrderLine;
import com.ecommerce.demo.entities.ProductInventory;
import com.ecommerce.demo.exception.ProductException;
import com.ecommerce.demo.request.OrderLineCreateRequest;
import com.ecommerce.demo.request.ProductInventoryCreateRequest;
import com.ecommerce.demo.response.GenericResponse;
import com.ecommerce.demo.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class ProductInventoryController {

  @Autowired
  ProductInventoryService productInventoryService;

  @GetMapping("/getProductInventory")
  public ResponseEntity<GenericResponse<List<ProductInventory>>> getProductInventory (@RequestParam("id") Integer productId){
    List<ProductInventory> productInventoryList = productInventoryService.getProductInventory(productId);
    GenericResponse<List<ProductInventory>> response = new GenericResponse<>(productInventoryList,"","success","200");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<GenericResponse<String>> createProductInventory(@RequestBody ProductInventoryCreateRequest productInventoryCreateRequest) throws ProductException {
    Integer productInventoryId = productInventoryService.createInventoryforProduct(productInventoryCreateRequest);
    GenericResponse<String> response = new GenericResponse<>(String.valueOf(productInventoryId),"","success","200");
    ResponseEntity entity = new ResponseEntity<>(response, HttpStatus.OK);
    return entity;
  }
}
