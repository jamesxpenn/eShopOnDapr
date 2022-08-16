package com.chinasofti.mall.order.feign;


import com.chinasofti.mall.common.dto.Cart;
import com.chinasofti.mall.common.dto.Response;
import com.chinasofti.mall.common.dto.Shipping;
import com.chinasofti.mall.common.enums.ResponseEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "mall-cart")
public interface CartFeign {

    @RequestMapping(value ="/carts/listcards/{userid}",method = RequestMethod.GET)
    public List<Cart> listcards(@PathVariable Integer userid) ;

    @RequestMapping(value ="/carts/userid/{userid}/{productId}",method = RequestMethod.DELETE)
    public Response deleteCart(@PathVariable Integer userid, @PathVariable Integer productId);
}
