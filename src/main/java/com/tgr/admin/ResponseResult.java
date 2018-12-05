package com.tgr.admin;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable {

	private static final long serialVersionUID = -6789881961136385215L;

	public static final int CODE_SUCCESS = 200;
	public static final int CODE_CREATED = 201;
	public static final int CODE_ACCEPTED = 202;

	public static final int CODE_BAD_REQUEST = 400;
	public static final int CODE_UNAUTHORIZED = 401;
	public static final int CODE_FORBIDDEN = 403;

	public static final int CODE_INTERNAL_SERVER_ERROR = 500;

	private int code;
	private String message;
	private T data;

	private ResponseResult() {
	}

	public static <T> ResponseResult<T> getResult(T data) {
		return getResult(data, "");
	}

	public static <T> ResponseResult<T> getResult(T data, String msg) {
		return getResult(CODE_SUCCESS, data, msg);
	}

	public static <T> ResponseResult<T> getResult(int code, T data, String message) {
		ResponseResult<T> responseResult = new ResponseResult<T>();
		responseResult.setCode(code);
		responseResult.setData(data);
		responseResult.setMessage(message);
		return responseResult;
	}

	@SuppressWarnings("unchecked")
	public static <T> ResponseResult<T> getErrorResult(String message) {
		return (ResponseResult<T>) new ErrorResult(CODE_BAD_REQUEST, message);
	}

	@SuppressWarnings("unchecked")
	public static <T> ResponseResult<T> getUnauthorizedErrorResult(String message) {
		return (ResponseResult<T>) new ErrorResult(CODE_UNAUTHORIZED, message);
	}

	@SuppressWarnings("unchecked")
	public static <T> ResponseResult<T> getForbiddenErrorResult(String message) {
		return (ResponseResult<T>) new ErrorResult(CODE_FORBIDDEN, message);
	}

	// 服务器内部错误响应信息
	@SuppressWarnings("unchecked")
	public static <T> ResponseResult<T> getServerErrorResult(String message) {
		return (ResponseResult<T>) new ErrorResult(CODE_INTERNAL_SERVER_ERROR, message);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	private static class ErrorResult extends ResponseResult<String> implements Serializable {

		private static final long serialVersionUID = -333100795774710022L;

		public ErrorResult(int code, String message) {
			setCode(code);
			setMessage(message);
			setData("");
		}
	}

}
