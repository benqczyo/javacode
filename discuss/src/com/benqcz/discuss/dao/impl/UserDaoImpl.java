package com.benqcz.discuss.dao.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.benqcz.discuss.dao.UserDao;
import com.benqcz.discuss.domain.User;
import com.benqcz.discuss.util.XmlUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public User addUser(User user) {
		User result = null;
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
			result = user;
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
		User result = null;
		try {
			String xPathUser = String.format("//user[@username = '%s']", userName);
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

}
