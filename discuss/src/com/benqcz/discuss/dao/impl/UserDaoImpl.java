package com.benqcz.discuss.dao.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.benqcz.discuss.dao.UserDao;
import com.benqcz.discuss.domain.User;
import com.benqcz.discuss.util.xml.XmlUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean addUser(User user) {
		boolean result = false;
		try {
			Document document = XmlUtil.getDocument();
			Element userElement = document.getRootElement().addElement("user");
			Field[] fields = User.class.getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				PropertyDescriptor dp = new PropertyDescriptor(fieldName, User.class);
				userElement.addAttribute(fieldName, (String) dp.getReadMethod().invoke(user, null));
			}
			XmlUtil.write2Xml(document);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public User findUser(String userName, String password) {
		User result = null;
		try {
			String xPathUser = String.format("//user[@username = '%s' and @password = '%s']", userName, password);
			Node userNode = XmlUtil.getDocument().selectSingleNode(xPathUser);
			if (userNode != null) {
				result = new User();
				Field[] fields = User.class.getDeclaredFields();
				for (Field field : fields) {
					String fieldName = field.getName();
					PropertyDescriptor dp = new PropertyDescriptor(fieldName, User.class);
					String xPathAttr = String.format("@%s", field.getName());
					dp.getWriteMethod().invoke(result, userNode.valueOf(xPathAttr));
				}
			}
		}  catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public User findUserByName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		UserDao dao = new UserDaoImpl();
		User user = new User();
		user.setUsername("jack");
		user.setPassword("1234");
		user.setEmail("333");
		user.setBirthday("2011-10-01");
		System.out.println(dao.addUser(user));
	}
	
}
