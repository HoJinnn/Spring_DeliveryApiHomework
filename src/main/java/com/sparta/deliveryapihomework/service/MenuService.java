package com.sparta.deliveryapihomework.service;

import com.sparta.deliveryapihomework.model.Menu;
import com.sparta.deliveryapihomework.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository repository;

    public void registerMenu(List<Menu> menus) {
        repository.saveAll(menus);
    }

    public List<Menu> findByRestaurantId(Long restaurantId) {
        return repository.findByRestaurnatId(restaurantId);
    }


}
