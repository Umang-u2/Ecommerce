package com.ecommerce.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer userId;

  @Column(length = 30)
  private String userName;

  @Column(length = 30)
  private String firstName;

  @Column(length = 30)
  private String lastName;

  @Column(length = 30)
  private String email;

  @CreationTimestamp
  private Date createdOn;

  @UpdateTimestamp
  private Date updatedOn;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "addressId", referencedColumnName = "addressId")
  @JsonIgnore
  private Address address;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Order> listOrder;

}
