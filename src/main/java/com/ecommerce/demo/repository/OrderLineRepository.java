package com.ecommerce.demo.repository;

import com.ecommerce.demo.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {

  @Query("select o from OrderLine o where o.order.orderId = :id")
  List<OrderLine> getByOrder(Integer id);


}
