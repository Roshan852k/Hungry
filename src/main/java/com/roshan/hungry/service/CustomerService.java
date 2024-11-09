package com.roshan.hungry.service;

import com.roshan.hungry.dto.CustomerRequest;
import com.roshan.hungry.dto.CustomerLoginRequest;
import com.roshan.hungry.entity.Customer;
import com.roshan.hungry.helper.EncryptionService;
import com.roshan.hungry.helper.JWTHelper;
import com.roshan.hungry.mapper.CustomerMapper;
import com.roshan.hungry.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;

    /*
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }
    */

    // private final CustomerRepo customerRepo;
    // private final CustomerMapper customerMapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        repo.save(customer);
        return "Customer Created Successfully";
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

    /*
    public String deleteUserByEmail(CustomerDeleteRequest deleteRequest) {
        repo.deleteByEmail(deleteRequest.email());
        return "Customer deleted successfully";
    }
    */

    public String deleteUserByEmail(String email) {
        Customer customer = repo.findByEmail(email);
        if (customer != null) {
            repo.delete(customer);
            return "Customer deleted successfully";
        } else {
            return "Customer not found";
        }
    }
}
