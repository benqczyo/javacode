package com.benqcz.ikanke.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.benqcz.ikanke.domain.impl.AccountBean;
import com.benqcz.ikanke.exception.SendMailException;

public class MailUtils {
	public static void sendMail(final AccountBean account) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Properties props = new Properties();
				props.setProperty("mail.transport.protocol", "smtp");
				props.setProperty("mail.smtp.auth", "true");
				props.setProperty("mail.host", "localhost");
				props.setProperty("mail.debug", "true");
				Session session = Session.getDefaultInstance(props);
				Message message = new MimeMessage(session);
				try {
					message.setFrom(new InternetAddress("admin@ikanke.com"));
					message.setRecipient(RecipientType.TO, new InternetAddress(account.getEmail()));
					message.setSubject("×¢²áÈ·ÈÏ");
					String link = "http://localhost:8080/ikanke/router?action=check&code=" + account.getCode();
					String content = String.format("<p style='color: red;'>»¶Ó­×¢²áiKankeÕË»§<p><p>µã»÷Á´½Ó¼¤»îÄúµÄÕËºÅ<a href='%s'>%s</a></p>", link, link);
					message.setContent(content, "text/html;charset=UTF-8");
					message.saveChanges();
					message.writeTo(new FileOutputStream(new File("d://mail.txt")));
//					Transport transport = session.getTransport();
//					transport.connect("admin@ikanke.com", "sorry");
//					transport.sendMessage(message, message.getAllRecipients());
//					transport.close();
				} catch (Exception e) {
					throw new SendMailException(e);
				}
				
			}
		}).start();
	}
}
