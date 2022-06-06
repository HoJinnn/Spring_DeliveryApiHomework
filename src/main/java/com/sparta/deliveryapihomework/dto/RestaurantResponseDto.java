package com.sparta.deliveryapihomework.dto;

import com.sparta.deliveryapihomework.model.Restaurant;
import lombok.*;

@Getter
@NoArgsConstructor
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private int minOrderPrice;
    private int deliveryFee;

    public RestaurantResponseDto(Long id, String name, int minOrderPrice, int deliveryFee) {
        this.id = id;
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;
    }
}
