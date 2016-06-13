package utils;

import java.util.UUID;

public abstract class IdUtils {
	public static String generateId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
