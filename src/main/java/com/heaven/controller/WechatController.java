package com.heaven.controller;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heaven.eunms.ExceptionEunm;
import com.heaven.exception.SellException;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

@Controller
@RequestMapping("/wechat")
public class WechatController {
	@Autowired
	private WxMpService wxMpService;
	@GetMapping("/authorize")
	public String authorize(@RequestParam("returnUrl") String returnUrl) throws Exception{
		String url = "http://heaven.natapp1.cc/sell/wechat/userInfo";
		String result = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl, "UTF-8"));
		return "redirect:" + result;
	}
	@GetMapping("/userInfo")
	public String userInfo(@RequestParam("code") String code,@RequestParam("state") String returnUrl){
		WxMpOAuth2AccessToken accessToken = new WxMpOAuth2AccessToken();
		try {
			accessToken = wxMpService.oauth2getAccessToken(code);
		} catch (WxErrorException e) {
			throw new SellException(ExceptionEunm.WECHAT_MP_ERROR);
		}
		String openId = accessToken.getOpenId();
		System.out.println(openId);
		return "redirect:" + returnUrl + "?openid" + openId;
		
	}
}
