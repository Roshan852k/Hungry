package com.roshan.hungry.repo;

import com.roshan.hungry.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    //Customer findByEmail(String email);
    Optional<Customer> findByEmail(String email);
    void deleteByEmail(String email);
}