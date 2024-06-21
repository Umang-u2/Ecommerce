package com.ecommerce.demo.controller;

import com.ecommerce.demo.entities.Order;
import com.ecommerce.demo.entities.OrderLine;
import com.ecommerce.demo.exception.OrderException;
import com.ecommerce.demo.request.OrderCreateRequest;
import com.ecommerce.demo.response.GenericResponse;
import com.ecommerce.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  OrderService orderService;

  @PostMapping("/create")
  public Order createOrder(@RequestBody OrderCreateRequest orderCreateRequest, @RequestParam("id") Integer userId) throws OrderException {
    return orderService.createOrder(orderCreateRequest,userId);
  }

  @GetMapping("/checkout")
  public ResponseEntity<GenericResponse<OrderLine>> getOrderDetails (@RequestParam("id") Integer orderId){
    List<Order> order = orderService.getOrderFromId(orderId);
    GenericResponse<Order> response = new GenericResponse<>(order.get(0),"","success","200");
    return new ResponseEntity(response, HttpStatus.OK);
  }
}
