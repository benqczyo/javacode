package com.benqcz.ikanke.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.benqcz.ikanke.domain.impl.AccountBean;
import com.benqcz.ikanke.utils.MailUtils;

public class TestMailUtils {

	@Test
	public void testSendMail() {
		try {
			AccountBean account = new AccountBean();
			account.setEmail("benqcz@ikanke.com");
			MailUtils.sendMail(account);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
