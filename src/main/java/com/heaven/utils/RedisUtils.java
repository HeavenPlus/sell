package com.heaven.utils;
/**
 * 配置redis相关的常量
 * @author Administrator
 *
 */
public class RedisUtils {
	//存储token的key前缀
	public static final String TOKEN_PREFIX = "token_%s";
	//token过期时间（秒）
	public static final Integer EXPIRE = 7200;
}
