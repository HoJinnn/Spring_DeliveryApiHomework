package com.sparta.deliveryapihomework.model;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class OrderMenu extends Timestamped{

    @GeneratedValue
    @Id
    @Column(name = "ORDER_MENU_ID")
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_ID")
    Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    Order order; 
}
