package com.heaven.utils;

import java.util.Random;

public class KeyUtils {
	public static synchronized String getNumber() {

		long str = new Random().nextInt(100000) + System.currentTimeMillis();
		String number = String.valueOf(str);
		return number;
	}
}
