package com.chinasofti.pay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinasofti.pay.entity.PaymentInfo;
import com.chinasofti.pay.enums.PayPlatformEnum;
import com.chinasofti.pay.mapper.PaymentInfoMapper;
import com.chinasofti.pay.service.PaymentInfoService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements PaymentInfoService {

    @Autowired
    PaymentInfoMapper paymentInfoMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void create(String orderNo, Integer amount) {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOrderNo(orderNo);
        paymentInfo.setPaymentType(PayPlatformEnum.WX.getName());
        paymentInfo.setPayerTotal(amount);
        paymentInfo.setTransactionId(UUID.randomUUID().toString());
        paymentInfo.setTradeType("NATIVE");
        paymentInfo.setTradeState("SUCCESS");

        paymentInfoMapper.insert(paymentInfo);

        //发送消息，通知订单服务修改订单状态
        amqpTemplate.convertAndSend("order.exchange","order.pay", orderNo);
    }
}
