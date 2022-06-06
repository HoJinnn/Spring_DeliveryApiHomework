package com.sparta.deliveryapihomework.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class MenuResponseDto {
    private Long id;
    private String name;
    private int price;

    public MenuResponseDto(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
