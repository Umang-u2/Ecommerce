package com.ecommerce.demo.service;

import com.ecommerce.demo.entities.OrderLine;
import com.ecommerce.demo.entities.Order;
import com.ecommerce.demo.entities.Product;
import com.ecommerce.demo.entities.ProductInventory;
import com.ecommerce.demo.exception.OrderLineException;
import com.ecommerce.demo.exception.ProductException;
import com.ecommerce.demo.repository.OrderLineRepository;
import com.ecommerce.demo.repository.ProductInventoryRepository;
import com.ecommerce.demo.request.OrderLineCreateRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderLineService {

  @Autowired
  OrderService orderService;

  @Autowired
  ProductService productService;

  @Autowired
  ProductInventoryService productInventoryService;

  @Autowired
  OrderLineRepository orderLineRepository;

  @Autowired
  ProductInventoryRepository productInventoryRepository;


  private Order getOrder(Integer orderId) throws OrderLineException {
    List<Order> orderList = orderService.getOrderFromId(orderId);
    if(orderList == null || orderList.isEmpty()){
      throw new OrderLineException("No Order associated with this cart");
    }
    return orderList.get(0);
  }

  private Product getProductById(Integer productId) throws OrderLineException {
    List<Product> productList = productService.getProductById(productId);
    if(productList == null || productList.isEmpty()){
      throw new OrderLineException("Product not present in the store");
    }
    return productList.get(0);
  }

  @Transactional(rollbackOn = {OrderLineException.class})
  public Integer createOrderLine(OrderLineCreateRequest orderLineCreateRequest) throws OrderLineException, ProductException {
    Order order = getOrder(orderLineCreateRequest.getOrderId());

    Product product = getProductById(orderLineCreateRequest.getProductId());
    System.out.print(product.getProductInventory().getProductInventoryId());

    Double totalPrice = product.getPrice() * orderLineCreateRequest.getQuantity();

    OrderLine orderLine = OrderLine.builder()
            .order(order)
            .product(product)
            .quantity(orderLineCreateRequest.getQuantity())
            .totalPrice(totalPrice)
            .build();

    ProductInventory productInventory = orderLine.getProduct().getProductInventory();

    if(productInventory == null){
      throw new ProductException("Inventory is not set for the added item");
    }
    if((productInventory.getSupply()-productInventory.getDemand())>=orderLine.getQuantity()) {
      productInventory.setDemand(orderLine.getProduct().getProductInventory().getDemand() + orderLine.getQuantity());
    } else {
      throw new ProductException("Product is out of stock");
    }
    orderLine = orderLineRepository.save(orderLine);
    List<OrderLine> orderLineList = order.getOrderLines();
    orderLineList.add(orderLine);
    order.setOrderLines(orderLineList);
    double totalAmount = 0.0;
    for (OrderLine line : orderLineList) {
      totalAmount = totalAmount + line.getTotalPrice();
    }
    order.setAmountToPay(totalAmount+(order.getTaxes()/100)*totalAmount);

    return orderLine.getOrderLineId();
  }

  public List<OrderLine> getOrderLinesFromOrder(Integer id){
    return orderLineRepository.getByOrder(id);
  }

/*  public Integer removeItemFromOrderLine(Integer productId,Integer orderId) {
    Integer id = orderLineRepository.removeFromOrderLine(productId);
    double totalAmount = 0.0;
    List<OrderLine> orderLineList = orderLineRepository.getByOrder(orderId);
    for (OrderLine line : orderLineList) {
      totalAmount = totalAmount + line.getTotalPrice();
    }
    orderLineList.get(0).getOrder().setAmountToPay(totalAmount+(orderLineList.get(0).getOrder().getTaxes()/100)*totalAmount);
    return id;
  }*/

}
