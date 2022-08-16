package com.chinasofti.mall.product.controller;

import com.chinasofti.mall.common.dto.Product;
import com.chinasofti.mall.common.dto.ProductDetail;
import com.chinasofti.mall.common.dto.Response;
import com.chinasofti.mall.product.service.IProductService;
import com.chinasofti.mall.product.vo.ProductDetailVo;
import com.chinasofti.mall.product.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import org.apache.tomcat.jni.Proc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;



    @PostMapping("/products/ids")
    public List<Product> productList(@RequestBody Set<Integer> productIdSet) {

       return productService.selectByProductIdSet(productIdSet);
    }

    @GetMapping("/products")
    public Response<PageInfo> list(@RequestParam(required = false) Integer categoryId,
                                     @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return productService.list(categoryId, pageNum, pageSize);
    }

    @GetMapping("/products/{productId}")
    public Response<ProductDetail> detail(@PathVariable Integer productId) {
        return productService.detail(productId);
    }

    @PutMapping("/products/")
    public int update( @RequestBody Product product) {
        return productService.update(product);
    }
}