package com.chinasofti.mall.shipping.service;

import com.chinasofti.mall.common.dto.Response;
import com.chinasofti.mall.common.dto.Shipping;
import com.chinasofti.mall.shipping.form.ShippingForm;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by xuepeng@chinasofti.com
 */
public interface IShippingService {
	Shipping findShippingByUidAndSID(Integer uid, Integer shippingId);

	Response<Map<String, Integer>> add(Integer uid, ShippingForm form);

	Response delete(Integer uid, Integer shippingId);

	Response update(Integer uid, Integer shippingId, ShippingForm form);

	Response<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);

	List<Shipping>  getShipByIDSet(Set<Integer> shippingIdSet);

	Shipping  getShipByShipID(Integer shippingId);
}
