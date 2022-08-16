package com.chinasofti.mall.shipping.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by xuepeng@chinasofti.com
 */
@Data
public class ShippingForm {

	@NotBlank
	private String receiverName;

	@NotBlank
	private String receiverPhone;

	@NotBlank
	private String receiverMobile;

	@NotBlank
	private String receiverProvince;

	@NotBlank
	private String receiverCity;

	@NotBlank
	private String receiverDistrict;

	@NotBlank
	private String receiverAddress;

	@NotBlank
	private String receiverZip;
}
