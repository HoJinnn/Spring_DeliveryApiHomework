package com.sparta.deliveryapihomework.controller;

import com.sparta.deliveryapihomework.dto.RestaurantCreateRequestDto;
import com.sparta.deliveryapihomework.model.Restaurant;
import com.sparta.deliveryapihomework.repository.RestaurantRepository;
import com.sparta.deliveryapihomework.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    //음식점 등록 API
    @PostMapping("/restaurant/register")
    public ResponseEntity<String> registerRestaurant(@RequestBody @Valid RestaurantCreateRequestDto requestDto) {
        // TODO: 2022-06-03: 최소주문금액: 100원 단위로만 입력. (아닐시 error 발생)
        // TODO: 2022-06-03: 배달료: 500원 단위로만 입력. (아닐시 error 발생)

        if(requestDto.getMinOrderPrice() % 100 != 0){
            return ResponseEntity.badRequest().body("최소 주문 금액은 100원 단위로 입력해주세요!");
        }
        if (requestDto.getDeliveryFee() % 500 != 0) {
            return ResponseEntity.badRequest().body("배달료는 500원 단위로 입력해주세요!");
        }

        restaurantService.register(requestDto);

        return ResponseEntity.accepted().body("매장 정보 등록이 완료되었습니다!");
    }

    //등록된 모든 음식점 조회
    @GetMapping("/restaurant")
    public ResponseEntity<List<Restaurant>> findRestaurant() {
        // TODO: 2022-06-03: 등록된 모든 음식점 정보 조회(name, minOrderPrice, deliveryFee)

        List<Restaurant> restaurantList;
        restaurantList = restaurantService.findAll();

        return ResponseEntity.ok().body(restaurantList);
    }
}