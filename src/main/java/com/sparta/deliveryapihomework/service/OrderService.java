package com.sparta.deliveryapihomework.service;

import com.sparta.deliveryapihomework.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
}
