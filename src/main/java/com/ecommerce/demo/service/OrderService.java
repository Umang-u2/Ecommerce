package com.ecommerce.demo.service;

import com.ecommerce.demo.entities.Order;
import com.ecommerce.demo.entities.OrderStatus;
import com.ecommerce.demo.entities.User;
import com.ecommerce.demo.exception.OrderException;
import com.ecommerce.demo.repository.OrderRepository;
import com.ecommerce.demo.repository.UserRepository;
import com.ecommerce.demo.request.OrderCreateRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepo;

  @Autowired
  private UserRepository userRepo;

  public Order createOrder (OrderCreateRequest orderCreateRequest, Integer userId) throws OrderException {
    User user = userRepo.findByUserId(userId).get(0);
    if(user == null){
      throw new OrderException("User is not present in the system");
    }
    Order order = orderCreateRequest.toOrder();
    order.setUser(user);
    return orderRepo.save(order);
  }

  @Transactional
  public List<Order> getOrderFromId(Integer orderId){
    List<Order> orderList = orderRepo.findByOrderId(orderId);
    Order order = orderList.get(0);
    order.setOrderStatus(OrderStatus.ACCEPTED);
    orderRepo.updateOrderStatus(OrderStatus.ACCEPTED,order.getOrderId());
    return orderList;
  }
}
