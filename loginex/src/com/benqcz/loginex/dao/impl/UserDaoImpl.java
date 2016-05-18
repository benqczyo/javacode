package com.benqcz.loginex.dao.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.benqcz.loginex.dao.UserDao;
import com.benqcz.loginex.domain.UserBean;
import com.benqcz.utils.XmlUtils;

import com.sun.org.apache.commons.beanutils.BeanUtils;

public class UserDaoImpl implements UserDao {

	private XmlUtils xmlUtils = XmlUtils.getUtilInstance(UserDaoImpl.class.getClassLoader().getResource("users.xml").getPath(), "UTF-8");

	public boolean addUser(UserBean user) {
		boolean result = false;
		try {
			Element userElement = xmlUtils.open().getRootElement().addElement("user");
			Field[] fields = UserBean.class.getDeclaredFields();
			for (Field userBeanField : fields) {
				userBeanField.setAccessible(true);
				String userBeanFieldName = userBeanField.getName();
				String userBeanFieldValue = (String) userBeanField.get(user);
				userElement.addAttribute(userBeanFieldName, userBeanFieldValue);
			}
			xmlUtils.write2Xml();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	public boolean delUserById(String id) {
		boolean result = false;
		try {
			String xPath = String.format("//user[@id='%s']", id);
			Document doc = xmlUtils.open();
			Node userNode = doc.selectSingleNode(xPath);
			if (userNode != null) doc.getRootElement().remove(userNode);
			xmlUtils.write2Xml();
			result = true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	public List<UserBean> findAllUsers() {
		List<UserBean> result = null;
		try {
			Field[] userBeanFields = UserBean.class.getDeclaredFields();
			List<Element> userElements = xmlUtils.open().getRootElement().elements("user");
			if (userElements != null) {
				if (result == null) result = new LinkedList<UserBean>();
				for (Element userElement : userElements) {
					UserBean userBean = new UserBean();
					for (Field userBeanField : userBeanFields) {
						userBeanField.setAccessible(true);
						String userBeanFieldName = userBeanField.getName();
						userBeanField.set(userBean, userElement.valueOf(String.format("@%s", userBeanFieldName)));
					}
					result.add(userBean);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	public UserBean findUserById(String id) {
		UserBean result = null;
		String xPath = String.format("//user[@id='%s']", id);
		try {
			Node userNode = xmlUtils.open().selectSingleNode(xPath);
			if (userNode != null) {
				result = new UserBean();
				Field[] userBeanFields = UserBean.class.getDeclaredFields();
				for (Field userBeanField : userBeanFields) {
					userBeanField.setAccessible(true);
					String fieldName = userBeanField.getName();
					userBeanField.set(result, userNode.valueOf(String.format("@%s", fieldName)));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	public boolean updateUser(UserBean user) {
		boolean result = false;
		try {
			String xPath = String.format("//user[@id='%s']", user.getId());
			Node userNode = xmlUtils.open().selectSingleNode(xPath);
			if (userNode != null) {
				Class userBeanClass = UserBean.class;
				List<Node> attrs = userNode.selectNodes("@*");
				for (Node node : attrs) {
					Field userBeanField = userBeanClass.getDeclaredField(node.getName());
					if (userBeanField != null) {
						userBeanField.setAccessible(true);
						node.setText((String) userBeanField.get(user));
					}
				}
			}
			xmlUtils.write2Xml();
			result = true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return false;
	}

}
