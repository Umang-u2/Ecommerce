package com.ecommerce.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UserOrder")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Integer orderId;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;


  @Enumerated(value = EnumType.STRING)
  private OrderStatus orderStatus;

  @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
  @JsonIgnore
  private List<OrderLine> orderLines = new ArrayList<>();

  @Column
  private Double amountToPay;

  @Column
  private Double taxes;

}
