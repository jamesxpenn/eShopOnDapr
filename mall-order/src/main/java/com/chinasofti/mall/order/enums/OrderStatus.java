package com.chinasofti.mall.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {

    /**
     * 未支付
     */
    NOTPAY(10),


    /**
     * 支付成功
     */
    SUCCESS(20),

    /**
     * 已关闭
     */
    CLOSED(60),

    /**
     * 已取消
     */
    CANCEL(0);

    /**
     * 类型
     */
    private final int code;
}
