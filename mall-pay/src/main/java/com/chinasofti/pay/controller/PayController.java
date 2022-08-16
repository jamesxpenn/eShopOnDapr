package com.chinasofti.pay.controller;

import com.chinasofti.mall.common.dto.Response;
import com.chinasofti.pay.entity.PaymentInfo;
import com.chinasofti.pay.form.PayForm;
import com.chinasofti.pay.service.PaymentInfoService;
import com.chinasofti.pay.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "商品管理")
@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private PaymentInfoService paymentInfoService;

    @PostMapping("/native")
    public Response  create(@RequestBody PayForm payForm

    ) {
        paymentInfoService.create(payForm.getOrderNo(), payForm.getAmount());

        return Response.success();
    }

    @ApiOperation("测试发送消息")
    @GetMapping("/send")
    public void send(){
        amqpTemplate.convertAndSend("payNotify", "hello");
    }

    @ApiOperation("测试发送消息")
    @GetMapping("/send1")
    public void send1(){
        //amqpTemplate.convertAndSend("payNotify", "hello");
        amqpTemplate.convertAndSend("order.exchange","order.pay", 1);
        //amqpTemplate.convertAndSend("order-event-exchange","order.pay","hello");
    }
}
