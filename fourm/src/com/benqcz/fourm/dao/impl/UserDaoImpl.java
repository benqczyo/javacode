package com.benqcz.fourm.dao.impl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.benqcz.fourm.dao.UserDao;
import com.benqcz.fourm.domain.UserBean;
import com.benqcz.fourm.utils.XmlUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public UserBean addUser(UserBean user) {
		UserBean result = null;
		try {
			Document document = XmlUtils.getDocument();
			Element userElement = document.getRootElement().addElement("user");
			Field[] fields = UserBean.class.getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				PropertyDescriptor pd = new PropertyDescriptor(fieldName, UserBean.class);
				userElement.addAttribute(fieldName, (String) pd.getReadMethod().invoke(user, null));
			}
			XmlUtils.write2Xml(document);
			result = user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public UserBean findUser(String username, String password) {
		UserBean result = null;
		String xPath = String.format("//user[@username='%s' and @password='%s']", username, password);
		try {
			Node userNode = XmlUtils.getDocument().selectSingleNode(xPath);
			if (userNode != null) {
				result = new UserBean();
				Field[] fields = UserBean.class.getDeclaredFields();
				for (Field field : fields) {
					String fieldName = field.getName();
					PropertyDescriptor pd = new PropertyDescriptor(fieldName, UserBean.class);
					pd.getWriteMethod().invoke(result, 
							userNode.valueOf(String.format("@%s", fieldName)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public UserBean findUserByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
