package com.chinasofti.mall.common.dto;

import com.chinasofti.mall.common.enums.ResponseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Created by xuepeng@chinasofti.com
 */
@Data
@NoArgsConstructor
public class Response<T> {

	private Integer status;

	private String msg;

	private T data;

	private Response(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	private Response(Integer status, T data) {
		this.status = status;
		this.data = data;
	}

	public static <T> Response<T> successByMsg(String msg) {
		return new Response<>(ResponseEnum.SUCCESS.getCode(), msg);
	}

	public static <T> Response<T> success(T data) {
		return new Response<>(ResponseEnum.SUCCESS.getCode(), data);
	}

	public static <T> Response<T> success() {
		return new Response<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc());
	}

	public static <T> Response<T> error(ResponseEnum responseEnum) {
		return new Response<>(responseEnum.getCode(), responseEnum.getDesc());
	}

	public static <T> Response<T> error(ResponseEnum responseEnum, String msg) {
		return new Response<>(responseEnum.getCode(), msg);
	}

//	public static <T> Response<T> error(ResponseEnum responseEnum, BindingResult bindingResult) {
//		return new Response<>(responseEnum.getCode(),
//				Objects.requireNonNull(bindingResult.getFieldError()).getField() + " " + bindingResult.getFieldError().getDefaultMessage());
//	}
}
