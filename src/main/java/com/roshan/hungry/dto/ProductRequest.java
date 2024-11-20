package com.roshan.hungry.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        @NotNull(message="Product name is required")
        @NotEmpty(message="Product name is required")
        @NotBlank(message = "Product name is required")
        @JsonProperty("name")
        String name,

        @NotNull(message="Product price is required")
        @NotEmpty(message="Product price is required")
        @NotBlank(message = "Product price is required")
        @JsonProperty("price")
        Integer price

) {
}
