package com.sparta.deliveryapihomework.repository;

import com.sparta.deliveryapihomework.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
