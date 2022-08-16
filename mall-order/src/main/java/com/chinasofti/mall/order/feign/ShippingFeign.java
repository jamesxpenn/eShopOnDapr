package com.chinasofti.mall.order.feign;

import com.chinasofti.mall.common.dto.Response;
import com.chinasofti.mall.common.dto.Shipping;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@FeignClient(value = "mall-shipping")
public interface ShippingFeign {
    @RequestMapping(value ="/shippings/usid/{userid}/{shippingId}",method = RequestMethod.GET)
    public Shipping selectByUidAndShippingId(@PathVariable Integer userid, @PathVariable Integer shippingId);

    @RequestMapping(value ="/shippings/idset",method = RequestMethod.POST)
    public List<Shipping> shippingList(@RequestBody Set<Integer> productIdSet);


    @RequestMapping(value ="/shippings/shipid/{shippingId}",method = RequestMethod.GET)
    public Shipping getShipByShipID(@PathVariable Integer shippingId);
}
