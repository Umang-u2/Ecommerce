package com.ecommerce.demo.repository;

import com.ecommerce.demo.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
