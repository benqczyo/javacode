package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import sun.misc.BASE64Encoder;

public class Base64Utils {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		System.out.println(new BASE64Encoder().encode(str.getBytes()));
	}
}
