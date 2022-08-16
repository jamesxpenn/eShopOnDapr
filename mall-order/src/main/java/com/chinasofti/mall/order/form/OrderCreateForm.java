package com.chinasofti.mall.order.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by xuepeng@chinasofti.com
 */
@Data
public class OrderCreateForm {

	@NotNull
	private Integer shippingId;
}
