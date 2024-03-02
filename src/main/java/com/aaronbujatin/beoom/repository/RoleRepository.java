package com.aaronbujatin.beoom.repository;

import com.aaronbujatin.beoom.entitiy.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String role);



}
