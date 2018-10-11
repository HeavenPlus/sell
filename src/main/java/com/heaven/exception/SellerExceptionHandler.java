package com.heaven.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.heaven.eunms.ExceptionEunm;
import com.heaven.utils.ResultUtils;
import com.heaven.vo.ResultVo;

@ControllerAdvice
public class SellerExceptionHandler {

	@ExceptionHandler(value=SellerException.class)
	public ModelAndView handlerException(){
		Map<String, Object> map = new HashMap<>();
		map.put("msg", ExceptionEunm.NOT_LOGIN.getMsg());
		map.put("url", "/sell/login");
		return new ModelAndView("login/error",map);
	}
	
	@ExceptionHandler(value=SellException.class)
	@ResponseBody
	public ResultVo handlerSellException(SellException e){
		return ResultUtils.error(e.getCode(),e.getMessage());
	}
}
