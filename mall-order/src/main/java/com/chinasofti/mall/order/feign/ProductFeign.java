package com.chinasofti.mall.order.feign;

import com.chinasofti.mall.common.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@FeignClient(value = "mall-product")
public interface ProductFeign {

    @RequestMapping(value ="/products/ids",method = RequestMethod.POST)
    public List<Product> productList(@RequestBody Set<Integer> productIdSet);


    @RequestMapping(value ="/products/",method = RequestMethod.PUT)
    public int update( @RequestBody Product product);
}
