package com.chinasofti.mall.common.dto;

import lombok.Data;

/**
 * Created by xuepeng@chinasofti.com
 */
@Data
public class Cart {

	private Integer productId;

	private Integer quantity;

	private Boolean productSelected;

	public Cart() {
	}

	public Cart(Integer productId, Integer quantity, Boolean productSelected) {
		this.productId = productId;
		this.quantity = quantity;
		this.productSelected = productSelected;
	}
}
