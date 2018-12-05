package com.tgr.admin.Exception;

public class LoginFailedException extends RuntimeException {

	private static final long serialVersionUID = -4892829646916753554L;

	public LoginFailedException() {
		super("手机或密码不正确");
	}

}
