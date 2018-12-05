package com.tgr.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tgr.admin.Exception.ForbiddenException;
import com.tgr.admin.Exception.LoginFailedException;
import com.tgr.admin.Exception.UnauthorizedException;

@ControllerAdvice
public class BaseController {

	protected static final int PAGE_SIZE = 20;


	/*public Persion authorized(HttpServletRequest request) {
		Long persion_id = (Long) request.getAttribute("persion_id");
		Persion persion = persion_id == null ? null : persionService.find(persion_id);
		if (persion == null) {
			throw new UnauthorizedException();
		}
		return persion;
	}*/

	@ExceptionHandler({LoginFailedException.class, ForbiddenException.class})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseResult<String> processLoginFailException(LoginFailedException e) {
		return ResponseResult.getForbiddenErrorResult(e.getMessage());
	}

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseResult<String> processUnauthorizedException(UnauthorizedException e) {
		return ResponseResult.getUnauthorizedErrorResult(e.getMessage());
	}

	@ExceptionHandler(value = { IllegalStateException.class, NumberFormatException.class })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseResult<String> processIllegalStateAndNumberFormatException(Exception e) {
		return ResponseResult.getErrorResult(e.getMessage());
	}

	@ExceptionHandler(value = { IllegalArgumentException.class })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseResult<String> processIllegalArgumentException(Exception e) {
		e.printStackTrace();
		return ResponseResult.getErrorResult(e.getMessage());
	}

	@ExceptionHandler(value = { MissingServletRequestParameterException.class })
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseResult<String> MissingServletRequestParameterException(Exception e) {
		e.printStackTrace();
		return ResponseResult.getErrorResult(e.getMessage());
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseResult<String> catchException(HttpServletRequest request, Exception e) {
		e.printStackTrace();
		return ResponseResult.getServerErrorResult(e.getMessage());
	}
}
