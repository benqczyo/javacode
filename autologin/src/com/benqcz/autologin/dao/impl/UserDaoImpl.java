package com.benqcz.autologin.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.benqcz.autologin.dao.UserDao;
import com.benqcz.autologin.domain.UserBean;
import com.benqcz.autologin.exception.DaoException;
import com.benqcz.utils.XmlUtils;

public class UserDaoImpl implements UserDao {

	private XmlUtils xmlUtils;

	public UserDaoImpl() {
		String path = UserDaoImpl.class.getClassLoader().getResource("users.xml").getPath();
		xmlUtils = XmlUtils.getUtilInstance(path, "UTF-8");
	}

	public boolean addUser(UserBean user) {
		boolean result = false;
		try {
			Element element = xmlUtils.open().getRootElement().addElement("user");
			Field[] fields = UserBean.class.getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				element.addAttribute(fieldName, BeanUtils.getProperty(user, fieldName));
			}
			xmlUtils.write2Xml();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
		return result;
	}

	public boolean delUserByName(String name) {
		boolean result = false;
		try {
			Document doc = xmlUtils.open();
			String xPath = String.format("//user[@name='%s']", name);
			Node node = doc.selectSingleNode(xPath);
			if (node != null) result = doc.getRootElement().remove(node);
			xmlUtils.write2Xml();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
		return result;
	}

	public List<UserBean> findAllUsers() {
		List<UserBean> result = null;
		try {
			Document doc = xmlUtils.open();
			List<Node> nodes = doc.selectNodes("//user");
			if (nodes != null) {
				result = new LinkedList<UserBean>();
				Field[] fields = UserBean.class.getDeclaredFields();
				for (Node node : nodes) {
					UserBean user = new UserBean();
					for (Field field : fields) {
						String fieldName = field.getName();
						BeanUtils.setProperty(user, fieldName, node.valueOf(String.format("@%s", fieldName)));
					}
					result.add(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
		return result;
	}

	public UserBean findUser(String name, String password) {
		UserBean result = null;
		try {
			String xPath = String.format("//user[@name='%s' and @password='%s']", name, password);
			Node node = xmlUtils.open().selectSingleNode(xPath);
			if (node != null) {
				result = new UserBean();
				Field[] fields = UserBean.class.getDeclaredFields();
				for (Field field : fields){
					String fieldName = field.getName();
					BeanUtils.setProperty(result, fieldName, node.valueOf(String.format("@%s", fieldName)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
		return result;
	}

	public UserBean findUserByName(String name) {
		UserBean result = null;
		try {
			String xPath = String.format("//user[@name='%s']", name);
			Node node = xmlUtils.open().selectSingleNode(xPath);
			if (node != null) {
				result = new UserBean();
				Field[] fields = UserBean.class.getDeclaredFields();
				for (Field field : fields){
					String fieldName = field.getName();
					BeanUtils.setProperty(result, fieldName, node.valueOf(String.format("@%s", fieldName)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
		return result;
	}

	public boolean updateUser(UserBean user) {
		boolean result = false;
		try {
			String xPath = String.format("//user[@name='%s']", user.getName());
			Node node = xmlUtils.open().selectSingleNode(xPath);
			if (node != null) {
				List<Node> attrs = node.selectNodes("@*");
				for (Node attr : attrs) {
					if (!attr.getName().equalsIgnoreCase("name")) 
						attr.setText(BeanUtils.getProperty(user, attr.getName()));
				}
			}
			xmlUtils.write2Xml();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
		return result;
	}

}
