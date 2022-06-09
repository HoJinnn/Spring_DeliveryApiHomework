package com.sparta.deliveryapihomework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "ORDERS")
@NoArgsConstructor
@Entity
public class Order extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Order_ID")
    private Long id;

    private int totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURNAT_ID")
    Restaurant restaurant;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderMenu> orderMenus = new ArrayList<>();


    public void countTotalPrice(List<OrderMenu> orderMenus) {
        int sum = 0;
        for (OrderMenu orderMenu : orderMenus) {
            sum += orderMenu.getPrice();
        }
        sum += this.restaurant.getDeliveryFee();

        this.totalPrice = sum;
    }

    @Builder
    public Order(Restaurant restaurant, int totalPrice) {
        this.restaurant = restaurant;
        this.totalPrice = totalPrice;
    }
}
