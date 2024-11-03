package com.roshan.hungry.service;

import com.roshan.hungry.dto.CustomerRequest;
import com.roshan.hungry.dto.CustomerLoginRequest;
import com.roshan.hungry.dto.CustomerResponse;
import com.roshan.hungry.entity.Customer;
import com.roshan.hungry.mapper.CustomerMapper;
import com.roshan.hungry.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo repo;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }

    public String loginCustomer(CustomerLoginRequest loginRequest) {
        Customer customerOpt = repo.findByEmail(loginRequest.email());

        if (customerOpt != null) {
            if (loginRequest.password().equals(customerOpt.getPassword())) {
                return "Login successful!";
            }
            else {
                return "Invalid username or password";
            }
        } else {
            return "username not found";
        }
    }
}
