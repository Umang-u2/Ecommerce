package com.ecommerce.demo.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInventoryCreateRequest {

  private Integer productId;

  private Integer supply;

}
