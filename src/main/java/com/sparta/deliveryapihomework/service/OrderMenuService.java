package com.sparta.deliveryapihomework.service;

import com.sparta.deliveryapihomework.repository.OrderMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderMenuService {

    private final OrderMenuRepository orderMenuRepository;
}
