package com.tgr.admin.Exception;

public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 6999506054325493160L;

	public ForbiddenException() {
		super("没有权限");
	}
}
