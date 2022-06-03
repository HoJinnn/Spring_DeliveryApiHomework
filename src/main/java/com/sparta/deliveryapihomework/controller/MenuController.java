package com.sparta.deliveryapihomework.controller;

import com.sparta.deliveryapihomework.dto.MenuRegisterRequestDto;
import com.sparta.deliveryapihomework.model.Menu;
import com.sparta.deliveryapihomework.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public ResponseEntity<MenuRegisterRequestDto> registerMenu(@PathVariable Long id, @RequestBody MenuRegisterRequestDto requestDto) {
        // TODO: 2022-06-04: 같은 음식점 내에서는 음식 이름 중복 불가
        // TODO: 2022-06-04: 100원 단위로 입력, 허용범위: 100 ~ 1,000,000원


       return ResponseEntity.ok(requestDto);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public ResponseEntity<List<Menu>> findAllMenu(@PathVariable Long id) {
        // TODO: 2022-06-04: 하나의 음식점에 등록된 모든 음식 정보 조회


        return ResponseEntity.ok()
    }
}
