package dao.impl;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import utils.XmlUtils;

import dao.UserDao;
import domain.UserBean;
import exception.DaoException;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean addUser(UserBean user) {
		boolean result = false;
		try {
			//创建元素
			Document doc = XmlUtils.open();
			Element element = doc.getRootElement().addElement("user");
			//得到bean的所有字段
			Field[] fields = UserBean.class.getDeclaredFields();
			//遍历字段添加元素属性
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldName = field.getName();
				element.addAttribute(fieldName, (String) field.get(user));
			}
			XmlUtils.save(doc);
			result = true;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public boolean delUserById(String id) {
		boolean result = false;
		try {
			//查找指定id的用户
			Document doc = XmlUtils.open();
			String xPath = String.format("//user[@id='%s']", id);
			Node node = doc.selectSingleNode(xPath);
			if (node != null)
				doc.getRootElement().remove(node);
			XmlUtils.save(doc);
			result = true;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public List<UserBean> findAllUsers() {
		List<UserBean> result = null;
		try {
			List<Node> nodes = XmlUtils.open().selectNodes("//user");
			Field[] fields = UserBean.class.getDeclaredFields();
			for (Node node : nodes) {
				if (result == null)
					result = new LinkedList<UserBean>();
				UserBean user = new UserBean();
				for (Field field : fields) {
					field.setAccessible(true);
					String fieldName = field.getName();
					field.set(user, node.valueOf("@" + fieldName));
				}
				result.add(user);
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public UserBean findUser(String name, String password) {
		UserBean result = null;
		String xPath = String.format("//user[@name='%s' and @password='%s']", name, password);
		try {
			Node node = XmlUtils.open().selectSingleNode(xPath);
			if (node != null) {
				result = new UserBean();
				Field[] fields = UserBean.class.getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					field.set(result, node.valueOf("@" + field.getName()));
				}
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public UserBean findUserById(String id) {
		UserBean result = null;
		try {
			//查找指定id的用户
			Document doc = XmlUtils.open();
			String xPath = String.format("//user[@id='%s']", id);
			Node node = doc.selectSingleNode(xPath);
			if (node != null) {
				result = new UserBean();
				Field[] fields = UserBean.class.getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					field.set(result, node.valueOf("@" + field.getName()));
				}
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public boolean updateUser(UserBean user) {
		return false;
	}

}
