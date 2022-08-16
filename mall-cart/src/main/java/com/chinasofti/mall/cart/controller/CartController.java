package com.chinasofti.mall.cart.controller;

import com.chinasofti.mall.cart.form.CartAddForm;
import com.chinasofti.mall.cart.form.CartUpdateForm;
import com.chinasofti.mall.cart.intercepter.CartLoginInterceptor;
import com.chinasofti.mall.cart.service.ICartService;
import com.chinasofti.mall.cart.vo.CartVo;
import com.chinasofti.mall.cart.vo.ResponseVo;
import com.chinasofti.mall.common.dto.Cart;
import com.chinasofti.mall.common.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xuepeng@chinasofti
 */
@RestController
public class CartController {
	@Autowired
	private ICartService cartService;



	@GetMapping("/carts/listcards/{userid}")
	public List<Cart> listcards(@PathVariable Integer userid) {
		//User user = (User) session.getAttribute(MallConst.CURRENT_USER);

		//Integer userId= Integer.valueOf(CartLoginInterceptor.getUserId());

		List<Cart> cartList = cartService.listForCart(userid).stream()
		.filter(Cart::getProductSelected)
		.collect(Collectors.toList());

		return cartList;
	}

	@GetMapping("/carts")
	public ResponseVo<CartVo> list() {
		//User user = (User) session.getAttribute(MallConst.CURRENT_USER);

		Integer userId= Integer.valueOf(CartLoginInterceptor.getUserId());
		return cartService.list(userId);
	}

	@PostMapping("/carts")
	public ResponseVo<CartVo> add(@Valid @RequestBody CartAddForm cartAddForm) {

		return cartService.add(Integer.valueOf(CartLoginInterceptor.getUserId()), cartAddForm);


	}

	@PutMapping("/carts/{productId}")
	public ResponseVo<CartVo> update(@PathVariable Integer productId,
									 @Valid @RequestBody CartUpdateForm form) {
		return cartService.update(Integer.valueOf(CartLoginInterceptor.getUserId()), productId, form);


	}

	@DeleteMapping("/carts/userid/{userid}/{productId}")
	public Response cart(@PathVariable Integer userid, @PathVariable Integer productId) {
		return cartService.deleteByUidAndProID(userid, productId);
	}

	@DeleteMapping("/carts/{productId}")
	public ResponseVo<CartVo> delete(@PathVariable Integer productId) {
		return cartService.delete(Integer.valueOf(CartLoginInterceptor.getUserId()), productId);

	}

	@PutMapping("/carts/selectAll")
	public ResponseVo<CartVo> selectAll() {
		return cartService.selectAll(Integer.valueOf(CartLoginInterceptor.getUserId()));

	}

	@PutMapping("/carts/unSelectAll")
	public ResponseVo<CartVo> unSelectAll() {
		return cartService.unSelectAll(Integer.valueOf(CartLoginInterceptor.getUserId()));


	}

	@GetMapping("/carts/products/sum")
	public ResponseVo<Integer> sum() {
		return cartService.sum(Integer.valueOf(CartLoginInterceptor.getUserId()));

	}


//	@GetMapping("/carts/products/sum")
//	public ResponseVo<Integer> sum() {
//
//		System.out.println(123);
//		return null;
//	}
//
//	@GetMapping("/carts")
//	public ResponseVo<CartVo> list() {
//		System.out.println("123123");
//		return null;
//	}
}
