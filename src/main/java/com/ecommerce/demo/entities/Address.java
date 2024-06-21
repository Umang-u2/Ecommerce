package com.ecommerce.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer addressId;

  @OneToOne(mappedBy = "address")
  private User user;

  @Enumerated
  private AddressType type;

  @Column
  private String addressLine1;

  @Column
  private String addressLine2;

  @Column
  private String city;

  @Column
  private String country;

  @Column
  private String zipcode;

}
