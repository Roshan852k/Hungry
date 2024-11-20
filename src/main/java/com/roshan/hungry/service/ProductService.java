package com.roshan.hungry.service;

import com.roshan.hungry.dto.ProductRequest;
import com.roshan.hungry.dto.ProductResponse;
import com.roshan.hungry.entity.Product;
import com.roshan.hungry.exception.ProductNotFoundException;
import com.roshan.hungry.mapper.ProductMapper;
import com.roshan.hungry.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo repo;
    private final ProductMapper mapper;

    public String addProduct(ProductRequest request) {
        Product product = mapper.toEntity(request);
        repo.save(product);
        return "Product Added Successfully";
    }

    public Product getProduct(String name) {
        return repo.findByName(name)
                .orElseThrow(() -> new ProductNotFoundException(
                        format("Cannot found Customer:: No customer found with the provided ID:: %s", name)
                ));
    }

    public ProductResponse retrieveProduct(String name) {
        Product product = getProduct(name);
        return mapper.toProductResponse(product);
    }

    public String updateProductPrice(String name, Integer newPrice) {
        Product product = repo.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with name: " + name));
        product.setPrice(newPrice);
        repo.save(product);

        return "Product Price Updated successfully";
    }

    public String deleteProductByName(String name) {
        Product product = repo.findByName(name).get();
        if (product != null) {
            repo.delete(product);
            return "Product deleted successfully";
        } else {
            return "Product not found";
        }
    }

     public List<Product> getTop2Product() {
        return repo.getTop2Product();
    }

}
