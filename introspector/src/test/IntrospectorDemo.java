package test;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import beans.UserBean;


public class IntrospectorDemo {
	
	public static void main(String[] args) throws Exception {
		
		/*BeanInfo beanInfo = Introspector.getBeanInfo(UserBean.class);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor pd : pds) {
			System.out.println(pd.getName());
		}*/
		
		ConvertUtils.register(new Converter() {

			@Override
			public Object convert(Class type, Object object) {
				Object result = null;
				try {
					if (object instanceof String) {
						result = new SimpleDateFormat("yyyy-MM-dd").parse((String) object);
					} else {
						result = new SimpleDateFormat("yyyy-MM-dd").format((Date) object);
					}
				} catch (ParseException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				return result;
			}
			
		}, Date.class);
		
		UserBean user = new UserBean();
		BeanUtils.setProperty(user, "birthday", "2007-09-09");
		String result = BeanUtils.getProperty(user, "birthday");
		System.out.println(user);
		
	}

}
