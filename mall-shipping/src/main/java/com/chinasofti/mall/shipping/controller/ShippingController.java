package com.chinasofti.mall.shipping.controller;

import com.chinasofti.mall.common.dto.Response;
import com.chinasofti.mall.common.dto.Shipping;
import com.chinasofti.mall.shipping.form.ShippingForm;
import com.chinasofti.mall.shipping.intercepter.UserLoginInterceptor;
import com.chinasofti.mall.shipping.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * Created by xuepeng@chinasofti.com
 */
@RestController
public class ShippingController {

	@Autowired
	private IShippingService shippingService;

	@PostMapping("/shippings/idset")
	public List<Shipping> shippingList(@RequestBody Set<Integer> productIdSet) {

		return shippingService.getShipByIDSet(productIdSet);
	}

	@GetMapping("/shippings/usid/{userid}/{shippingId}")
	public Shipping shipping(@PathVariable Integer userid, @PathVariable Integer shippingId) {
		return shippingService.findShippingByUidAndSID(userid,shippingId);
	}

	@PostMapping("/shippings")
	public Response add(@Valid @RequestBody ShippingForm form) {
		return shippingService.add(Integer.valueOf(UserLoginInterceptor.getUserId()), form);
	}

	@DeleteMapping("/shippings/{shippingId}")
	public Response delete(@PathVariable Integer shippingId) {
		return shippingService.delete(Integer.valueOf(UserLoginInterceptor.getUserId()), shippingId);
	}

	@PutMapping("/shippings/{shippingId}")
	public Response update(@PathVariable Integer shippingId,
							 @Valid @RequestBody ShippingForm form) {
		Integer userId= Integer.valueOf(UserLoginInterceptor.getUserId());
		return shippingService.update(userId, shippingId, form);
	}

	@GetMapping("/shippings")
	public Response list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
						   @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		return shippingService.list(Integer.valueOf(UserLoginInterceptor.getUserId()), pageNum, pageSize);
	}

	@GetMapping("/shippings/shipid/{shippingId}")
	public Shipping shipping(@PathVariable Integer shippingId) {
		return shippingService.getShipByShipID(shippingId);
	}
}
