package com.ecommerce.demo.repository;

import com.ecommerce.demo.entities.OrderStatus;
import com.ecommerce.demo.entities.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductInventoryRepository extends JpaRepository<ProductInventory,Integer> {

  @Query("select p from ProductInventory p where p.product.productId = :id")
  List<ProductInventory> getProductInventory(Integer id);


}
