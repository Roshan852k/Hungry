package com.roshan.hungry.service;

import com.roshan.hungry.dto.CustomerRequest;
import com.roshan.hungry.dto.CustomerLoginRequest;
import com.roshan.hungry.dto.CustomerResponse;
import com.roshan.hungry.entity.Customer;
import com.roshan.hungry.exception.CustomerNotFoundException;
import com.roshan.hungry.helper.EncryptionService;
import com.roshan.hungry.helper.JWTHelper;
import com.roshan.hungry.mapper.CustomerMapper;
import com.roshan.hungry.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import java.util.UUID;

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
        Customer customerOpt = repo.findByEmail(loginRequest.email()).get();
        System.out.println(customerOpt);
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

   public Customer getCustomer(String email) {
       return repo.findByEmail(email)
               .orElseThrow(() -> new CustomerNotFoundException(
                       format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
               ));
   }

   public CustomerResponse retrieveCustomer(String email) {
       Customer customer = getCustomer(email);
       return mapper.toCustomerResponse(customer);
   }


    public String login(CustomerLoginRequest request) {
        Customer customer = getCustomer(request.email());
        if(!encryptionService.validates(request.password(), customer.getPassword())) {
            return "Wrong Password or Email";
        }

        return jwtHelper.generateToken(request.email());
    }


    public String deleteUserByEmail(String email) {
        Customer customer = repo.findByEmail(email).get();
        if (customer != null) {
            repo.delete(customer);
            return "Customer deleted successfully";
        } else {
            return "Customer not found";
        }
    }
}
