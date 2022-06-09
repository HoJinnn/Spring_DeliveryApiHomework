package com.sparta.deliveryapihomework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenuResponseDto {
    private String name;
    private int quantity;
    private int price;

}

