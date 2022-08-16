package com.chinasofti.mall.order.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {
    private Integer id;

    private Long orderNo;

    private Integer userId;

    private Integer shippingId;

    private BigDecimal payment;

    private Integer paymentType;

    private Integer postage;

    private Integer status;

    private String codeUrl;

    private Date paymentTime;

    private Date sendTime;

    private Date endTime;

    private Date closeTime;

//    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonProperty("create_time")
    private String createTime;

    private Date updateTime;
}