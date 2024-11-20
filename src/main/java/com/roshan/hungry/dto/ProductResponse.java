package com.roshan.hungry.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductResponse(
        @JsonProperty("name")
        String name,
        @JsonProperty("price")
        Integer price
){
}
