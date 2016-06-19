package com.benqcz.ikanke.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public abstract class SHA1Utils {
	public static String encoding(String str) {
		try {
			return new BASE64Encoder().encode(MessageDigest.getInstance("SHA1").digest(str.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
