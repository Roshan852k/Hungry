package com.roshan.hungry.repo;


import com.roshan.hungry.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    /*
    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Product findByProductID(@Param("id") Integer id);
     */


    Optional<Product> findByName(String name);


    //void deleteByProductId(Long productId);
}
