package com.ecommerce.demo.controller;

import com.ecommerce.demo.entities.OrderLine;
import com.ecommerce.demo.exception.OrderLineException;
import com.ecommerce.demo.exception.ProductException;
import com.ecommerce.demo.request.OrderLineCreateRequest;
import com.ecommerce.demo.response.GenericResponse;
import com.ecommerce.demo.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderLine")
public class OrderLineController {

  @Autowired
  OrderLineService orderLineService;

  @PostMapping("/create")
  public ResponseEntity<GenericResponse<String>> CreateOrderLine(@RequestBody OrderLineCreateRequest orderLineCreateRequest) throws OrderLineException {
    try {
      Integer cartId = orderLineService.createOrderLine(orderLineCreateRequest);

      GenericResponse<String> response = new GenericResponse<>(String.valueOf(cartId), "", "success", "200");
      ResponseEntity entity = new ResponseEntity<>(response, HttpStatus.OK);
      return entity;
    } catch (ProductException e){
      GenericResponse<String> response = new GenericResponse<>("",e.getMessage(),"failure","400");
      ResponseEntity entity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
      return entity;
    }
  }

  @GetMapping("/getCartDetails")
  public ResponseEntity<GenericResponse<List<OrderLine>>> getOrderLinesByOrderId (@RequestParam("id") Integer orderId){
    List<OrderLine> orderLineList = orderLineService.getOrderLinesFromOrder(orderId);
    GenericResponse<List<OrderLine>> response = new GenericResponse<>(orderLineList,"","success","200");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
