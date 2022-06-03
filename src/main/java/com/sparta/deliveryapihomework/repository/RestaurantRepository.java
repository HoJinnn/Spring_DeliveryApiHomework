package com.sparta.deliveryapihomework.repository;

import com.sparta.deliveryapihomework.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
