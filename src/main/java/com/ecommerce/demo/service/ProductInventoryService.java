package com.ecommerce.demo.service;

import com.ecommerce.demo.entities.Product;
import com.ecommerce.demo.entities.ProductInventory;
import com.ecommerce.demo.exception.OrderException;
import com.ecommerce.demo.exception.OrderLineException;
import com.ecommerce.demo.exception.ProductException;
import com.ecommerce.demo.repository.ProductInventoryRepository;
import com.ecommerce.demo.repository.ProductRepository;
import com.ecommerce.demo.request.ProductInventoryCreateRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInventoryService {

  @Autowired
  ProductInventoryRepository productInventoryRepository;

  @Autowired
  ProductRepository productRepository;


  public List<ProductInventory> getProductInventory(Integer id){
    return productInventoryRepository.getProductInventory(id);
  }


  public Integer createInventoryforProduct(ProductInventoryCreateRequest productInventoryCreateRequest) throws ProductException {
    List<Product> productList = productRepository.findByProductId(productInventoryCreateRequest.getProductId());
    Product product = productList.get(0);
    if(productList.isEmpty() || productList == null){
      throw new ProductException("Product not available in the system");
    }
    ProductInventory productInventory = ProductInventory.builder()
            .product(product)
            .supply(productInventoryCreateRequest.getSupply())
            .demand(0)
            .build();
    product.setProductInventory(productInventory);
    productInventory = productInventoryRepository.save(productInventory);
    return productInventory.getProductInventoryId();
  }
}
