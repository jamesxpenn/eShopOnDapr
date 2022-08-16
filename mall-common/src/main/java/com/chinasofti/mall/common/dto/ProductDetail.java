package com.chinasofti.mall.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by xuepeng@chinasofti.com
 */
@Data
@NoArgsConstructor
public class ProductDetail {

	private Integer id;

	private Integer categoryId;

	private String name;

	private String subtitle;

	private String mainImage;

	private String subImages;

	private String detail;

	private BigDecimal price;

	private Integer stock;

	private Integer status;

	private Date createTime;

	private Date updateTime;
}
