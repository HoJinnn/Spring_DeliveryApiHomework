package com.sparta.deliveryapihomework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenuRequestDto {
    private Long id;
    private int quantity;
}
