package com.ecommerce.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInventory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_inventory_id")
  private Integer productInventoryId;

  @OneToOne(mappedBy = "productInventory")
  private Product product;

  @Column
  private Integer supply;

  @Column
  private Integer demand;

}
