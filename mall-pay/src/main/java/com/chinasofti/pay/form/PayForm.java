package com.chinasofti.pay.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PayForm {
    private String orderNo;

    private Integer amount;
}
