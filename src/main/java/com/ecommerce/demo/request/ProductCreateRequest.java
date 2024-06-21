package com.ecommerce.demo.request;

import com.ecommerce.demo.entities.Address;
import com.ecommerce.demo.entities.Product;
import com.ecommerce.demo.entities.ProductCategories;
import com.ecommerce.demo.entities.ProductInventory;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCreateRequest {

  private ProductCategories category;
  private String productName;
  private String description;
  private String size;
  private Double price;

  public Product toProduct() {

    return Product.builder()
            .productName(this.productName)
            .category(this.category)
            .description(this.description)
            .size(this.size)
            .price(this.price)
            .build();
  }
}
