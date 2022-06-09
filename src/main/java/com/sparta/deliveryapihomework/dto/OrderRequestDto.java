package com.sparta.deliveryapihomework.dto;

import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    private Long restaurantId;
    private List<OrderMenuRequestDto> foods;
}
