

package com.sparta.deliveryapihomework.dto;


import com.sparta.deliveryapihomework.model.Restaurant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class RestaurantRegisterRequestDto {

    private Long id;

    @NotBlank(message = "상호명을 입력해주세요.")
    private String name;

    @NotNull(message = "최소 주문 금액을 입력해주세요.")
    @Min(value = 1000, message = "최소 주문 금액은 1,000원 이상입니다.")
    @Max(value = 100000, message = "최대 주문 금액은 100,000원 이하입니다.")
    private int minOrderPrice;

    @NotNull(message = "기본 배달료를 입력해주세요.")
    @Min(value = 0)
    @Max(value = 10000, message = "최대 배달료는 10,000원 이하입니다.")
    private int deliveryFee;

    public RestaurantRegisterRequestDto(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.minOrderPrice = restaurant.getMinOrderPrice();
        this.deliveryFee = restaurant.getDeliveryFee();
    }
}