package com.chinasofti.mall.cart.service;

import com.chinasofti.mall.cart.form.CartAddForm;
import com.chinasofti.mall.cart.form.CartUpdateForm;
import com.chinasofti.mall.cart.vo.CartVo;
import com.chinasofti.mall.cart.vo.ResponseVo;
import com.chinasofti.mall.common.dto.Cart;
import com.chinasofti.mall.common.dto.Response;

import java.util.List;

/**
 * Created by xuepeng@chinasofti.com
 */
public interface ICartService {

	ResponseVo<CartVo> add(Integer uid, CartAddForm form);

	ResponseVo<CartVo> list(Integer uid);

	ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateForm form);

	ResponseVo<CartVo> delete(Integer uid, Integer productId);


	Response deleteByUidAndProID(Integer uid, Integer productId);


	ResponseVo<CartVo> selectAll(Integer uid);

	ResponseVo<CartVo> unSelectAll(Integer uid);

	ResponseVo<Integer> sum(Integer uid);

	List<Cart> listForCart(Integer uid);
}
