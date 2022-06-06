package com.sparta.deliveryapihomework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(uniqueConstraints =
        @UniqueConstraint(
        name = "MENU_UNIQUE",
        columnNames = {"NAME", "RESTAURANT_ID"}))
@NoArgsConstructor
@Getter
@Entity
public class Menu extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;


    public Menu(String name, int price, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }
}
