package com.chinasofti.mall.common.enums;

/**
 * Created by xuepeng@chinasofti.com
 */

import lombok.Getter;

@Getter
public enum PaymentTypeEnum {

	PAY_ONLINE(1),
	;

	Integer code;

	PaymentTypeEnum(Integer code) {
		this.code = code;
	}
}
