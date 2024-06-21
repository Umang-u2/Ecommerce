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
public class OrderLine {

  @Id
  @GeneratedValue( strategy = GenerationType.AUTO)
  @Column(name = "order_line_id")
  private Integer orderLineId;

  @Column
  private Double totalPrice;


  @ManyToOne(optional = false)
  @JoinColumn(name = "order_id", nullable = false)
  @JsonIgnore
  private Order order;

  @ManyToOne(optional = false)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;

}
