package com.chinasofti.mall.product.service;

import com.chinasofti.mall.common.dto.Product;
import com.chinasofti.mall.common.dto.ProductDetail;
import com.chinasofti.mall.common.dto.Response;
import com.github.pagehelper.PageInfo;
import com.chinasofti.mall.product.vo.ResponseVo;

import java.util.List;
import java.util.Set;

/**
 * Created by xuepeng@chinasofti.com
 */
public interface IProductService {

	Response<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize);

	Response<ProductDetail> detail(Integer productId);

	List<Product>  selectByProductIdSet(Set<Integer> productIdSet);

	int update(Product product);
}
