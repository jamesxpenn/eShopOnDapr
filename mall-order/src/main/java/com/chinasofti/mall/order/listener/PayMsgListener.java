package com.chinasofti.mall.order.listener;


import com.chinasofti.mall.order.dao.OrderMapper;
import com.chinasofti.mall.order.enums.OrderStatus;
import com.chinasofti.mall.order.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PayMsgListener {

    @Autowired
    OrderMapper orderMapper;


    //收到微信的通知，修改订单状态
//    @RabbitHandler
    @RabbitListener(queues = "order.queue")
    public void process(String ordreNo){
        log.info("接收到消息{}",ordreNo);
//
        Order order = orderMapper.selectByOrderNo(Long.valueOf(ordreNo));
        order.setStatus(OrderStatus.SUCCESS.getCode());
        orderMapper.updateByPrimaryKeySelective(order);

    }
}
