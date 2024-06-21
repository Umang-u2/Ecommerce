package com.ecommerce.demo.request;

import com.ecommerce.demo.entities.Address;
import com.ecommerce.demo.entities.AddressType;
import com.ecommerce.demo.entities.User;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateRequest {

  private String userName;
  private String firstName;
  private String lastName;
  private String email;
  private String addressLine1;
  private String addressLine2;
  private AddressType addressType;
  private String city;
  private String country;
  private String zipcode;


  public Address toAddress(){
    return Address.builder()
            .type(this.addressType)
            .addressLine1(this.addressLine1)
            .addressLine2(this.addressLine2)
            .city(this.city)
            .country(this.country)
            .zipcode(this.zipcode).build();

  }

  public User toUser() {
    return User.builder()
            .userName(this.userName)
            .firstName(this.firstName)
            .lastName(this.lastName)
            .email(this.lastName).build();
  }
}
