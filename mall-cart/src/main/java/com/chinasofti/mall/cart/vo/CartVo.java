package com.chinasofti.mall.cart.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车
 * Created by xuepeng@chinasofti.com
 */
@Data
public class CartVo {

	private List<CartProductVo> cartProductVoList;

	private Boolean selectedAll;

	private BigDecimal cartTotalPrice;

	private Integer cartTotalQuantity;
}
