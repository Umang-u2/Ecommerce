package com.ecommerce.demo.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineCreateRequest {

  private Integer orderId;

  private Integer productId;

  private Integer quantity;
}
