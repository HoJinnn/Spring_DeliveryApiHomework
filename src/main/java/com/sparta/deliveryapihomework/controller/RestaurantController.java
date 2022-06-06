package com.sparta.deliveryapihomework.controller;

import com.sparta.deliveryapihomework.dto.RestaurantRegisterRequestDto;
import com.sparta.deliveryapihomework.dto.RestaurantResponseDto;
import com.sparta.deliveryapihomework.model.Restaurant;
import com.sparta.deliveryapihomework.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    //음식점 등록 API
    @PostMapping("/restaurant/register")
    public ResponseEntity<RestaurantRegisterRequestDto> registerRestaurant(@RequestBody @Valid RestaurantRegisterRequestDto requestDto) {
        // TODO: 2022-06-03: 최소주문금액: 100원 단위로만 입력. (아닐시 error 발생)
        // TODO: 2022-06-03: 배달료: 500원 단위로만 입력. (아닐시 error 발생)

        if(requestDto.getMinOrderPrice() % 100 != 0){
            return ResponseEntity.badRequest().body(requestDto);
        }
        if (requestDto.getDeliveryFee() % 500 != 0) {
            return ResponseEntity.badRequest().body(requestDto);
        }

        return ResponseEntity.ok().body(new RestaurantRegisterRequestDto(restaurantService.register(requestDto)));
    }

    //등록된 모든 음식점 조회
    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantResponseDto>> findAllRestaurant() {
        // TODO: 2022-06-03: 등록된 모든 음식점 정보 조회(name, minOrderPrice, deliveryFee)

        List<Restaurant>restaurantList = restaurantService.findAll();

        List<RestaurantResponseDto> restaurantResponseDto = restaurantList.stream()
                .map(r -> new RestaurantResponseDto(r.getId(), r.getName(), r.getMinOrderPrice(), r.getDeliveryFee()))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(restaurantResponseDto);
    }
}