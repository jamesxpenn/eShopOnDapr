package com.chinasofti.mall.order.service;

import com.chinasofti.mall.common.dto.Response;
import com.chinasofti.mall.common.vo.OrderVo;
import com.github.pagehelper.PageInfo;

/**
 * Created by xuepeng@chinasofti.com
 */
public interface IOrderService {

	Response<OrderVo> create(Integer uid, Integer shippingId);

	Response<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);

	Response<OrderVo> detail(Integer uid, Long orderNo);

	Response cancel(Integer uid, Long orderNo);

	void paid(Long orderNo);
}
