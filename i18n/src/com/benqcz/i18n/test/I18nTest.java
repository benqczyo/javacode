package com.benqcz.i18n.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class I18nTest {

	public static void main(String[] args) {
		ResourceBundle bundle = ResourceBundle.getBundle("com.benqcz.i18n.resource.msg", Locale.UK);
		System.out.println(bundle.getString("hint"));
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
		String dateStr = df.format(new Date());
		System.out.println(dateStr);
	}

}
