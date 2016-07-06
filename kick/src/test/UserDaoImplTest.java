package test;

import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;
import utils.SHA1Utils;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.UserBean;

public class UserDaoImplTest {
	
	private UserDao dao = new UserDaoImpl();
	
	@Test
	public void testAddUser() {
		UserBean user = new UserBean();
		user.setId(UUID.randomUUID().toString());
		user.setName("jack");
		user.setPassword(SHA1Utils.encoding("sorry"));
		Assert.assertEquals(true, dao.addUser(user));
	}
	
	@Test
	public void testDelUserById() {
		Assert.assertEquals(true, dao.delUserById("0df84210-e6c8-4284-a61c-99ed65e598eb"));
	}
	
	@Test
	public void testAllUsers() {
		System.out.println(dao.findAllUsers());
	}
	
	@Test
	public void testFindUser() {
		Assert.assertNotNull(dao.findUser("jack", "3XsdAojw9RGL6Q71wOA6sbE3w/g="));
	}
	
	@Test
	public void testFindUserById() {
		Assert.assertNotNull(dao.findUserById("f241539d-ffbb-438f-89ab-0c5154500a39"));
	}
	
}
