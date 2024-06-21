package com.ecommerce.demo.request;

import com.ecommerce.demo.entities.Order;
import com.ecommerce.demo.entities.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreateRequest {

  private Double taxes;

  public Order toOrder() {
    return Order.builder()
            .orderStatus(OrderStatus.CREATED)
            .taxes(this.taxes)
            .build();

  }

}
