package com.sparta.deliveryapihomework.controller;

import com.sparta.deliveryapihomework.dto.OrderMenuRequestDto;
import com.sparta.deliveryapihomework.dto.OrderMenuResponseDto;
import com.sparta.deliveryapihomework.dto.OrderRequestDto;
import com.sparta.deliveryapihomework.dto.OrderResponseDto;
import com.sparta.deliveryapihomework.model.Order;
import com.sparta.deliveryapihomework.model.Restaurant;
import com.sparta.deliveryapihomework.service.OrderMenuService;
import com.sparta.deliveryapihomework.service.OrderService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;
    private final OrderMenuService orderMenuService;

    @PostMapping("/order/request")
    public OrderResponseDto requestOrder(@RequestBody OrderRequestDto requestDto) {
        log.info("requestDto = {}", requestDto);

        List<OrderMenuRequestDto> orderMenuDtos = requestDto.getFoods();
        for (OrderMenuRequestDto orderMenuDto : orderMenuDtos) {
            if (orderMenuDto.getQuantity() < 1 || orderMenuDto.getQuantity() > 100) {
                throw new IllegalArgumentException("주문 수량 범위: 1 ~ 100개");
            }
        }

        return orderService.requestOrder(requestDto);
    }

    @GetMapping("orders")
    public List<OrderResponseDto> findOrderList() {
        List<Order> orders = orderService.findAll();
        List<OrderResponseDto> responseDtos = new ArrayList<>();
        for (Order order : orders) {
            OrderResponseDto responseDto = orderService.findOrderResponseById(order.getId());
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }


}


