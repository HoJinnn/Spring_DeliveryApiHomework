package com.sparta.deliveryapihomework.service;

import com.sparta.deliveryapihomework.dto.*;
import com.sparta.deliveryapihomework.model.Menu;
import com.sparta.deliveryapihomework.model.Order;
import com.sparta.deliveryapihomework.model.OrderMenu;
import com.sparta.deliveryapihomework.model.Restaurant;
import com.sparta.deliveryapihomework.repository.MenuRepository;
import com.sparta.deliveryapihomework.repository.OrderMenuRepository;
import com.sparta.deliveryapihomework.repository.OrderRepository;
import com.sparta.deliveryapihomework.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public OrderResponseDto requestOrder(OrderRequestDto requestDto) {
        Restaurant restaurant = restaurantRepository.findById(requestDto.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("해당 음식점을 찾지 못했습니다!"));

        Order order = Order.builder().restaurant(restaurant).build();
        orderRepository.save(order);

        List<OrderMenu> orderMenus = new ArrayList<>();
        requestDto.getFoods()
                .forEach(m ->{
                    Menu menu = menuRepository.findById(m.getId()).orElseThrow(
                            () -> new IllegalArgumentException("해당 음식을 찾지 못했습니다!")
                    );
                    OrderMenu orderMenu = OrderMenu.builder()
                            .menu(menu)
                            .order(order)
                            .quantity(m.getQuantity())
                            .price(menu.getPrice() * m.getQuantity())
                            .build();

                    orderMenuRepository.save(orderMenu);
                    orderMenus.add(orderMenu);
                });

        order.countTotalPrice(orderMenus);

        if (order.getTotalPrice() - restaurant.getDeliveryFee() < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("최소 주문 금액 이상 주문해주세요!");
        }

        List<OrderMenuResponseDto> orderMenuResponseDto = orderMenus.stream()
                .map(m ->
                        OrderMenuResponseDto.builder()
                                .name(m.getMenu().getName())
                                .quantity(m.getQuantity())
                                .price(m.getPrice())
                                .build())
                .collect(Collectors.toList());

        return OrderResponseDto.builder()
                .restaurantName(restaurant.getName())
                .foods(orderMenuResponseDto)
                .deliveryFee(order.getRestaurant().getDeliveryFee())
                .totalPrice(order.getTotalPrice())
                .build();
    }
    @Transactional
    public OrderResponseDto findOrderResponseById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문을 찾지 못했어요!"));

        List<OrderMenuResponseDto> foods = toOrderMenuResponses(order);

        return OrderResponseDto.builder()
                .restaurantName(order.getRestaurant().getName())
                .foods(foods)
                .deliveryFee(order.getRestaurant().getDeliveryFee())
                .totalPrice(order.getTotalPrice())
                .build();
    }


    private List<OrderMenuResponseDto> toOrderMenuResponses(Order order) {
        return order.getOrderMenus().stream()
                .map(m ->
                        OrderMenuResponseDto.builder()
                                .name(m.getMenu().getName())
                                .quantity(m.getQuantity())
                                .price(m.getPrice())
                                .build())
                .collect(Collectors.toList());
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
