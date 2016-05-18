package com.benqcz.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public abstract class SHA1Utils {
	
	public static String encode(String value) {
		try {
			return new BASE64Encoder().encode(MessageDigest.getInstance("SHA1").digest(value.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
}
