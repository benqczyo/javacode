package cookie;

import java.util.HashSet;
import java.util.Set;

public class UserDB {
	
	protected static Set<User> users;
	
	static {
		users = new HashSet<User>();
		users.add(new User("benjamin", "123"));
		users.add(new User("sue", "321"));
		users.add(new User("jack", "231"));
	}
	
	public static boolean contains(User user) {
		return users.contains(user);
	}
	
	public static boolean add(User user) {
		return users.add(user);
	}
	
	public static boolean remove(User user) {
		return users.remove(user);
	}
	
}
