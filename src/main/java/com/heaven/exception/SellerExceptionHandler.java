package com.heaven.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.heaven.eunms.ExceptionEunm;

@ControllerAdvice
public class SellerExceptionHandler {

	@ExceptionHandler(value=SellerException.class)
	public ModelAndView handlerException(){
		Map<String, Object> map = new HashMap<>();
		map.put("msg", ExceptionEunm.NOT_LOGIN.getMsg());
		map.put("url", "/sell/login");
		return new ModelAndView("login/error",map);
	}
}
