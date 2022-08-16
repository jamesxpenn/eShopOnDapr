package com.chinasofti.mall.product.service.impl;

import com.chinasofti.mall.common.dto.Product;
import com.chinasofti.mall.common.dto.ProductDetail;
import com.chinasofti.mall.common.dto.Response;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.chinasofti.mall.product.dao.ProductMapper;
import com.chinasofti.mall.product.service.ICategoryService;
import com.chinasofti.mall.product.service.IProductService;
import com.chinasofti.mall.product.vo.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.chinasofti.mall.product.enums.ProductStatusEnum.*;
import static com.chinasofti.mall.common.enums.ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE;

/**
 * Created by xuepeng@chinasofti.com
 */
@Service
@Slf4j
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private ProductMapper productMapper;

	@Override
	public Response<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize) {
		Set<Integer> categoryIdSet = new HashSet<>();
		if (categoryId != null) {
			categoryService.findSubCategoryId(categoryId, categoryIdSet);
			categoryIdSet.add(categoryId);
		}

		PageHelper.startPage(pageNum, pageSize);
		List<Product> productList = productMapper.selectByCategoryIdSet(categoryIdSet);
		List<ProductVo> productVoList = productList.stream()
				.map(e -> {
					ProductVo productVo = new ProductVo();
					BeanUtils.copyProperties(e, productVo);
					return productVo;
				})
				.collect(Collectors.toList());

		PageInfo pageInfo = new PageInfo<>(productList);
		pageInfo.setList(productVoList);
		return Response.success(pageInfo);
	}

	@Override
	public Response<ProductDetail> detail(Integer productId) {
		Product product = productMapper.selectByPrimaryKey(productId);

		//只对确定性条件判断
		if (product.getStatus().equals(OFF_SALE.getCode())
				|| product.getStatus().equals(DELETE.getCode())) {
			return Response.error(PRODUCT_OFF_SALE_OR_DELETE);
		}

		ProductDetail productDetail = new ProductDetail();
		BeanUtils.copyProperties(product, productDetail);
		//敏感数据处理
		productDetail.setStock(product.getStock() > 100 ? 100 : product.getStock());
		return Response.success(productDetail);
	}

	@Override
	public List<Product> selectByProductIdSet(Set<Integer> productIdSet) {
		return productMapper.selectByProductIdSet(productIdSet);
	}

	@Override
	public int update(Product product) {
		return productMapper.updateByPrimaryKeySelective(product);
	}
}
