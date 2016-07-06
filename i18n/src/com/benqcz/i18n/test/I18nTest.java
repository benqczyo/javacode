package com.benqcz.i18n.test;

import java.awt.font.NumericShaper;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class I18nTest {

	public static void main(String[] args) throws ParseException {
//		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.CHINA);
//		String dateStr = df.format(new Date());
//		System.out.println(dateStr);
//		DateFormat tf = DateFormat.getTimeInstance(DateFormat.LONG, Locale.CHINA);
//		String timeStr = tf.format(new Date());
//		System.out.println(timeStr);
//		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
//		Number num = nf.parse("$198");
//		System.out.println(num);
		String str = "{0, date, full} has a {1}";
		String result = MessageFormat.format(str, new Object[] {new Date(), "an apple"});
		System.out.println(result);
	}

}
