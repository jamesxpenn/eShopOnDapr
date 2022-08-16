package com.chinasofti.mall.cart.model;

import com.chinasofti.mall.common.dto.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class State {
    private String key;
    private List<Cart> value;
}