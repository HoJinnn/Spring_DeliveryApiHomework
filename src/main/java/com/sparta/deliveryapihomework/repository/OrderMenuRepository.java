package com.sparta.deliveryapihomework.repository;

import com.sparta.deliveryapihomework.model.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Long> {
}
