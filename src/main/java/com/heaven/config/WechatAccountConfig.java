package com.heaven.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix="wechat")
public class WechatAccountConfig {
	private String mpAppId;
	private String mpAppSecret;
}
