package com.ecommerce.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  @Id
  @Column(name="product_id")
  @GeneratedValue( strategy = GenerationType.IDENTITY)
  private Integer productId;

  @Enumerated
  private ProductCategories category;

  @Column
  private String productName;

  @Column
  private String description;

  @Column
  private String size;

  @Column
  private Double price;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "product_inventory_id", referencedColumnName = "product_inventory_id")
  @JsonIgnore
  private ProductInventory productInventory;

}
