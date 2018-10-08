package com.heaven.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

@Component
public class WechatConfig {
	@Autowired
	private WechatAccountConfig wechatConfig;
	@Bean
	public WxMpService wxMpService(){
		WxMpService mpService = new WxMpServiceImpl();
		mpService.setWxMpConfigStorage(configStorage());
		return mpService;
	}
	
	public WxMpConfigStorage configStorage(){
		WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
		configStorage.setAppId(wechatConfig.getMpAppId());
		configStorage.setSecret(wechatConfig.getMpAppSecret());
		return configStorage;
	}
}
