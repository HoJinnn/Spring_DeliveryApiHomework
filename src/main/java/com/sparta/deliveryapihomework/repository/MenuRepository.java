package com.sparta.deliveryapihomework.repository;

import com.sparta.deliveryapihomework.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("Select this_ from Menu this_ where this_.restaurant.id = :id")
    List<Menu> findByRestaurnatId(@Param("id") Long id);
}
