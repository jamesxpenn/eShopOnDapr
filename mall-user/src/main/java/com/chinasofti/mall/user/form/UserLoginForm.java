package com.chinasofti.mall.user.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by xuepeng@chinasofti.com
 */
@Data
public class UserLoginForm {

	@NotBlank
	private String username;

	@NotBlank
	private String password;
}
