package com.ecommerce.demo.repository;

import com.ecommerce.demo.entities.Order;
import com.ecommerce.demo.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

  List<Order> findByOrderId(Integer id);

  @Modifying
  @Query("update Order o set o.orderStatus = :status where o.id=:id")
  Integer updateOrderStatus(OrderStatus status, Integer id);

}
