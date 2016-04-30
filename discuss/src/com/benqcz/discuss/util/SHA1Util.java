package com.benqcz.discuss.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public abstract class SHA1Util {

	public static String sha1(String message) {
		try {
			return new BASE64Encoder().encode(MessageDigest.getInstance("sha1").digest(message.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
