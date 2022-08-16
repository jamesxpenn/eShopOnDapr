package com.chinasofti.mall.order.task;

import com.chinasofti.mall.order.dao.OrderMapper;
import com.chinasofti.mall.order.enums.OrderStatus;
import com.chinasofti.mall.order.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class OrderTask {

    @Autowired
    OrderMapper orderMapper;

    /**
     * 从第0秒开始每隔30秒执行1次，查询创建超过5分钟，并且未付款的订单，修改未交易关闭
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void closeOrder() throws Exception {
        //orderMapper.selectByOrderNo()
        List<Order> orders = orderMapper.selectNoPayOrderTimeOut(OrderStatus.NOTPAY.getCode());

        orders.stream().forEach(order->{
            order.setStatus(OrderStatus.CLOSED.getCode());
            orderMapper.updateByPrimaryKeySelective(order);
        });

        System.out.println(123);

    }
}
