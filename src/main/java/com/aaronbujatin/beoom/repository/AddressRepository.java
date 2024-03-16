package com.aaronbujatin.beoom.repository;

import com.aaronbujatin.beoom.entitiy.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
