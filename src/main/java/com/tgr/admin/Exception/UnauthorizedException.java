package com.tgr.admin.Exception;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = -4892829646916753554L;

	public UnauthorizedException() {
		super("用户未登录");
	}

}
