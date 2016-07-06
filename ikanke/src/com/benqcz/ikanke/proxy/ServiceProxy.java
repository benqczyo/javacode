package com.benqcz.ikanke.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.benqcz.ikanke.exception.ServiceException;
import com.benqcz.ikanke.service.Service;
import com.benqcz.ikanke.service.impl.ServiceImpl;
import com.benqcz.ikanke.utils.DBCPUtils;

public abstract class ServiceProxy {
	public static Service getInstance() {
		final Class clazz = ServiceImpl.class;
		return (Service) Proxy.newProxyInstance(clazz.getClassLoader(),
				clazz.getInterfaces(), new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						boolean isTransactionRelated = false;
						try {
							if (method.getName().startsWith("del")) {
								isTransactionRelated = true;
								DBCPUtils.startTransaction();
							}
							return method.invoke(clazz.newInstance(), args);
						} catch (Exception e) {
							if (isTransactionRelated)
								DBCPUtils.rollback();
							throw new ServiceException(e);
						} finally {
							if (isTransactionRelated)
								DBCPUtils.commit();
							DBCPUtils.close();
						}
					}
				});
	}
}
