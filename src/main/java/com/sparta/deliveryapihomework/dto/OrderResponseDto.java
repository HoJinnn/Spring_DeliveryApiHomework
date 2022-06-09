package com.sparta.deliveryapihomework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private String restaurantName;
    private List<OrderMenuResponseDto> foods;
    private int deliveryFee;
    private int totalPrice;
}
