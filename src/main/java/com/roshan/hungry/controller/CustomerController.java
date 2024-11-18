package com.roshan.hungry.controller;

import com.roshan.hungry.dto.CustomerRequest;
import com.roshan.hungry.dto.CustomerLoginRequest;
import com.roshan.hungry.dto.CustomerResponse;
import com.roshan.hungry.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody @Valid CustomerLoginRequest loginRequest) {
        System.out.println("login request");
        return ResponseEntity.ok(customerService.loginCustomer(loginRequest));
    }

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("email") String email) {
        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }

    //@DeleteMapping("/delete/email")
    /*
    @PostMapping("/delete/email")
    public ResponseEntity<String> deleteUserByEmail(@RequestBody @Valid CustomerDeleteRequest deleteRequest) {
        return ResponseEntity.ok(customerService.deleteUserByEmail(deleteRequest));
    }
    */

    @DeleteMapping ("/delete")
    public ResponseEntity<String> deleteUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(customerService.deleteUserByEmail(email));
    }
}
