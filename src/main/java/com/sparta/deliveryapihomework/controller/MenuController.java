package com.sparta.deliveryapihomework.controller;

import com.sparta.deliveryapihomework.dto.MenuRegisterRequestDto;
import com.sparta.deliveryapihomework.dto.MenuResponseDto;
import com.sparta.deliveryapihomework.model.Menu;
import com.sparta.deliveryapihomework.model.Restaurant;
import com.sparta.deliveryapihomework.service.MenuService;
import com.sparta.deliveryapihomework.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class MenuController {

    private final RestaurantService restaurantService;
    private final MenuService menuService;
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerMenu(@PathVariable Long restaurantId, @RequestBody List<MenuRegisterRequestDto> requestDtos) {
        // TODO: 2022-06-04: 같은 음식점 내에서는 음식 이름 중복 불가
        // TODO: 2022-06-04: 100원 단위로 입력, 허용범위: 100 ~ 1,000,000원

        for(MenuRegisterRequestDto requestDto : requestDtos) {
            if (requestDto.getPrice() < 100 || requestDto.getPrice() > 1000000)
                throw new IllegalArgumentException("메뉴의 가격 범위는 100원 ~ 1,000,000원 입니다.");
            if (requestDto.getPrice() % 100 != 0)
                throw new IllegalArgumentException("메뉴 가격은 100원 단위로 입력해주세요.");
        }

        Restaurant restaurant = restaurantService.findById(restaurantId);

        List<Menu> menus = requestDtos.stream()
                        .map(m -> new Menu(m.getName(), m.getPrice(), restaurant))
                        .collect(Collectors.toList());

        menuService.registerMenu(menus);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<MenuResponseDto> findAllMenu(@PathVariable Long restaurantId) {
        // TODO: 2022-06-04: 하나의 음식점에 등록된 모든 음식 정보 조회

        List<Menu> menuList = menuService.findByRestaurantId(restaurantId);

        return menuList.stream()
                .map(m -> new MenuResponseDto(m.getId(), m.getName(), m.getPrice()))
                .collect(Collectors.toList());
    }
}
