package com.longmao.demo.modules.common.controller;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longmao.demo.modules.common.vo.Result;
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlerResolver {
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseBody
	public Result handerUnauthorizedExceptionn(UnauthorizedException exception) {
		return new Result(500,"没有权限");
	}

}
