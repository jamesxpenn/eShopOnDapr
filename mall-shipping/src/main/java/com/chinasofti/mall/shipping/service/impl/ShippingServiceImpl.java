package com.chinasofti.mall.shipping.service.impl;

import com.chinasofti.mall.common.dto.Response;
import com.chinasofti.mall.common.dto.Shipping;
import com.chinasofti.mall.common.enums.ResponseEnum;
import com.chinasofti.mall.shipping.dao.ShippingMapper;
import com.chinasofti.mall.shipping.form.ShippingForm;
import com.chinasofti.mall.shipping.service.IShippingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by xuepeng@chinasofti.com
 */
@Service
public class ShippingServiceImpl implements IShippingService {

	@Autowired
	private ShippingMapper shippingMapper;

	@Override
	public Shipping findShippingByUidAndSID(Integer uid, Integer shippingId) {
		Shipping shipping = shippingMapper.selectByUidAndShippingId(uid, shippingId);

		return shipping;
	}

	@Override
	public Response<Map<String, Integer>> add(Integer uid, ShippingForm form) {
		Shipping shipping = new Shipping();
		BeanUtils.copyProperties(form, shipping);
		shipping.setUserId(uid);
		int row = shippingMapper.insertSelective(shipping);
		if (row == 0) {
			return Response.error(ResponseEnum.ERROR);
		}

		Map<String, Integer> map = new HashMap<>();
		map.put("shippingId", shipping.getId());

		return Response.success(map);
	}

	@Override
	public Response delete(Integer uid, Integer shippingId) {
		int row = shippingMapper.deleteByIdAndUid(uid, shippingId);
		if (row == 0) {
			return Response.error(ResponseEnum.DELETE_SHIPPING_FAIL);
		}

		return Response.success();
	}

	@Override
	public Response update(Integer uid, Integer shippingId, ShippingForm form) {
		Shipping shipping = new Shipping();
		BeanUtils.copyProperties(form, shipping);
		shipping.setUserId(uid);
		shipping.setId(shippingId);
		int row = shippingMapper.updateByPrimaryKeySelective(shipping);
		if (row == 0) {
			return Response.error(ResponseEnum.ERROR);
		}
		return Response.success();
	}

	@Override
	public Response<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Shipping> shippings = shippingMapper.selectByUid(uid);
		PageInfo pageInfo = new PageInfo(shippings);
		return Response.success(pageInfo);
	}

	@Override
	public List<Shipping> getShipByIDSet(Set<Integer> shippingIdSet) {
		return shippingMapper.selectByIdSet(shippingIdSet);
	}

	@Override
	public Shipping getShipByShipID(Integer shippingId) {
		return this.shippingMapper.selectByPrimaryKey(shippingId);
	}
}
