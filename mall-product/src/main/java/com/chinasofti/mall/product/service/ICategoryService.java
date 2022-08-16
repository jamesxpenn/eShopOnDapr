package com.chinasofti.mall.product.service;

import com.chinasofti.mall.product.vo.CategoryVo;
import com.chinasofti.mall.product.vo.ResponseVo;

import java.util.List;
import java.util.Set;

/**
 * Created by xuepeng@chinasofti.com
 */
public interface ICategoryService {

	ResponseVo<List<CategoryVo>> selectAll();

	void findSubCategoryId(Integer id, Set<Integer> resultSet);
}
