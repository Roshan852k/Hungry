package com.roshan.hungry.repo;

import com.roshan.hungry.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
    void deleteByEmail(String email);
}