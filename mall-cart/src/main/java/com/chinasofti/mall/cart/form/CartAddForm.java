package com.chinasofti.mall.cart.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 添加商品
 * Created by xuepeng@chinasofti.com
 */
@Data
public class CartAddForm {

	@NotNull
	private Integer productId;

	private Boolean selected = true;
}
