package com.roshan.hungry.mapper;

import org.springframework.stereotype.Service;

import com.roshan.hungry.dto.CustomerRequest;
import com.roshan.hungry.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .address(request.address())
                .city(request.city())
                .pincode(request.pincode())
                .build();
    }
}