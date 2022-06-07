package com.sparta.deliveryapihomework.model;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Order extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Order_ID")
    private Long id;

    @Column(nullable = false)
    private int totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURNAT_ID")
    Restaurant restaurant;
}
