package com.chinasofti.mall.product.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by xuepeng@chinasofti.com
 */
@Data
public class CategoryVo {

	private Integer id;

	private Integer parentId;

	private String name;

	private Integer sortOrder;

	private List<CategoryVo> subCategories;
}
