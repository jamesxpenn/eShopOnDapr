package com.chinasofti.mall.cart.feign;


import com.chinasofti.mall.cart.vo.ProductDetailVo;
import com.chinasofti.mall.cart.vo.ResponseVo;
import com.chinasofti.mall.common.dto.ProductDetail;
import com.chinasofti.mall.common.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "mall-product")
public interface ProductFeign {

    @RequestMapping(value = "/products/{productId}",method = RequestMethod.GET)
    public Response<ProductDetail> getProduct(@PathVariable Integer productId);

}
