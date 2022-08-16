package com.chinasofti.mall.order.controller;

import com.chinasofti.mall.common.dto.Response;
import com.chinasofti.mall.common.vo.OrderVo;
import com.chinasofti.mall.order.form.OrderCreateForm;
import com.chinasofti.mall.order.intercepter.UserLoginInterceptor;
import com.chinasofti.mall.order.service.IOrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by xuepeng@chinasofti.com
 */
@RestController
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@PostMapping("/orders")
	public Response<OrderVo> create(@Valid @RequestBody OrderCreateForm form) {
		Integer userId= Integer.valueOf(UserLoginInterceptor.getUserId());

		return orderService.create(userId, form.getShippingId());
	}

	@GetMapping("/orders")
	public Response<PageInfo> list(@RequestParam Integer pageNum,
									 @RequestParam Integer pageSize) {
		return orderService.list(Integer.valueOf(UserLoginInterceptor.getUserId()), pageNum, pageSize);
	}

	@GetMapping("/orders/{orderNo}")
	public Response<OrderVo> detail(@PathVariable Long orderNo) {
		return orderService.detail(Integer.valueOf(UserLoginInterceptor.getUserId()), orderNo);
	}

	@GetMapping("/orders/uoid/{userid}/{orderNo}")
	public Response<OrderVo> orderVO(@PathVariable Integer userid,@PathVariable Long orderNo) {
		return orderService.detail(userid, orderNo);
	}

	@PutMapping("/orders/{orderNo}")
	public Response cancel(@PathVariable Long orderNo) {
		return orderService.cancel(Integer.valueOf(UserLoginInterceptor.getUserId()), orderNo);
	}

}
