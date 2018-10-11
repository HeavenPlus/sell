package com.heaven.aspect;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.heaven.exception.SellerException;
import com.heaven.utils.CookieUtil;
import com.heaven.utils.RedisUtils;

@Component
@Aspect
public class LoginAspect {
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Pointcut("execution(* com.heaven.controller.Seller*.*(..)) "
			+ "&& !execution(* com.heaven.controller.SellerInfoController.*(..))")
	public void verify(){}
	
	@Before("verify()")
	public void doVerify(){
		//获取request对象
		ServletRequestAttributes requestAttributes =  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		//检查cookie中是否有token
		Cookie cookie = CookieUtil.getCookie(request);
		if(cookie==null){
			throw new SellerException();
		} 
		//获取redis中保存的token
		try{
			String token = redisTemplate.opsForValue().get(String.format(RedisUtils.TOKEN_PREFIX, cookie.getValue()));
		}catch(SellerException e){
		}
	}
}
