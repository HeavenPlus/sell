package com.heaven.controller;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.heaven.exception.SellException;
import com.heaven.service.ISellerInfoService;
import com.heaven.utils.CookieUtil;
import com.heaven.utils.RedisUtils;
import com.heaven.validator.LoginValidator;

@Controller
@RequestMapping("/seller")
public class SellerInfoController {
	@Autowired
	private ISellerInfoService sellerInfoService;
	@Autowired
	private StringRedisTemplate redisTemplate;
	@PostMapping("/login")
	public ModelAndView login(@Valid LoginValidator loginValidator,BindingResult bind,Map<String, Object> map,HttpServletResponse response){
		if(bind.hasErrors()){
			for(FieldError error : bind.getFieldErrors()){
				String name = error.getField();
				map.put(name, error.getDefaultMessage());
			}
			return new ModelAndView("login/login",map);
		}
		try{
			sellerInfoService.login(loginValidator.getUsername(), loginValidator.getPassword());
		}catch(SellException e){
			map.put("msg", e.getMessage());
			return new ModelAndView("login/login",map);
		}
		//生成token
		String token = UUID.randomUUID().toString();
		//设置过期时间
		Integer expire = RedisUtils.EXPIRE;
		
		//存入redis
		redisTemplate.opsForValue().set(String.format(RedisUtils.TOKEN_PREFIX, token), token, expire, TimeUnit.SECONDS);
		
		//存入cookie
		CookieUtil.setCookie(response, "token", token, expire);
		
		return new ModelAndView("redirect:/seller/order/list");
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response){
		
		Cookie cookie = CookieUtil.getCookie(request);
		//清除redis中的token
		redisTemplate.opsForValue().getOperations().delete(String.format(RedisUtils.TOKEN_PREFIX, cookie.getValue()));
		//清除cookie中的token
		CookieUtil.setCookie(response, "token", null, 0);
		return new ModelAndView("login/login");
	}
}
