package com.chinasofti.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinasofti.pay.entity.PaymentInfo;

public interface PaymentInfoService extends IService<PaymentInfo> {

    void create(String orderId, Integer amount);
}
