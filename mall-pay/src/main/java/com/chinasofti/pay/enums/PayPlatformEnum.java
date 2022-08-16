package com.chinasofti.pay.enums;

import lombok.Getter;

/**
 * Created by 廖师兄
 */
@Getter
public enum PayPlatformEnum {

	//1-支付宝,2-微信
	ALIPAY("支付宝"),

	WX("微信"),
	;

	String name;

	PayPlatformEnum(String name) {
		this.name = name;
	}

}
