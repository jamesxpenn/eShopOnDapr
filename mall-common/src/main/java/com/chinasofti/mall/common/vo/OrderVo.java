package com.chinasofti.mall.common.vo;

import com.chinasofti.mall.common.dto.Shipping;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by xuepeng@chinasofti.com
 */
@Data
public class OrderVo {

	private Long orderNo;

	private BigDecimal payment;

	private Integer paymentType;

	private Integer postage;

	private Integer status;

	private Date paymentTime;

	private Date sendTime;

	private Date endTime;

	private Date closeTime;

	private String createTime;

	private List<OrderItemVo> orderItemVoList;

	private Integer shippingId;

	private Shipping shippingVo;
}
