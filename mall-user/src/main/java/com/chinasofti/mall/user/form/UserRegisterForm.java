package com.chinasofti.mall.user.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by xuepeng@chinasofti.com
 */
@Data
public class UserRegisterForm {

	//@NotBlank 用于 String 判断空格
	//@NotEmpty 用于集合
	//@NotNull
	@NotBlank
	private String username;

	@NotBlank
	private String password;

	@NotBlank
	private String email;
}
