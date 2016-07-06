package com.benqcz.fourm.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;


public abstract class SHA1Utils {
	public static String encode(String message) {
		try {
			return new BASE64Encoder().encode(MessageDigest.getInstance("SHA1").digest(message.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
