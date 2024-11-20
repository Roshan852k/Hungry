package com.roshan.hungry.mapper;

import com.roshan.hungry.dto.ProductRequest;
import com.roshan.hungry.dto.ProductResponse;
import com.roshan.hungry.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(product.getName(), product.getPrice());
    }
}
