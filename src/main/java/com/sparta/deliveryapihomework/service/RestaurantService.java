package com.sparta.deliveryapihomework.service;

import com.sparta.deliveryapihomework.dto.RestaurantCreateRequestDto;
import com.sparta.deliveryapihomework.model.Restaurant;
import com.sparta.deliveryapihomework.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant register(RestaurantCreateRequestDto requestDto) {
        //가게 정보 생성 메소드
        Restaurant restaurant = Restaurant.builder()
                .name(requestDto.getName())
                .minOrderPrice(requestDto.getMinOrderPrice())
                .deliveryFee(requestDto.getDeliveryFee())
                .build();

        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }
}
