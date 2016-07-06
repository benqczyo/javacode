package service.impl;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import domain.AccountBean;
import exception.AccountActiveFailedException;
import exception.AccountExistException;
import exception.AccountInactiveException;
import exception.AccountNotFoundException;
import exception.DaoException;
import exception.RegisterException;
import exception.SendMailException;
import service.AccountService;
import utils.SHA1Utils;

public class AccountServiceImpl implements AccountService {
	
	private AccountDao dao = new AccountDaoImpl();

	@Override
	public boolean register(final AccountBean account) {
		boolean result = false;
		account.setId(UUID.randomUUID().toString());
		account.setPassword(SHA1Utils.encoding(account.getPassword()));
		account.setCode(UUID.randomUUID().toString());
		account.setActived(false);
		try {
			result = dao.addAccount(account);
			sendmail(account);
		} catch (DaoException e) {
			throw new AccountExistException(e);
		} catch (Exception e) {
			throw new RegisterException(e);
		}
		return result;
	}

	private void sendmail(final AccountBean account) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Properties props = new Properties();
				props.setProperty("mail.transport.protocol", "smtp");
				props.setProperty("mail.smtp.auth", "true");
				props.setProperty("mail.host", "127.0.0.1");
				props.setProperty("mail.debug", "true");
				Session session = Session.getDefaultInstance(props);
				Message message = new MimeMessage(session);
				try {
					message.setFrom(new InternetAddress("admin@benqcz.com"));
					message.setRecipient(RecipientType.TO, new InternetAddress(account.getEmail()));
					message.setSubject("注册确认");
					String link = "http://localhost:8080/javamail/router?action=check&code=" + account.getCode();
					String content = String.format("<p style='color: red;'>中国南海三沙市，欢迎访问祖国的南海前哨http://www.sansha.gov.cn/<p><p>点击链接激活您的账号<a href='%s'>%s</a></p>", link, link);
					message.setContent(content, "text/html;charset=UTF-8");
					message.saveChanges();
					Transport transport = session.getTransport();
					transport.connect("admin@benqcz.com", "sorry");
					transport.sendMessage(message, message.getAllRecipients());
					transport.close();
				} catch (Exception e) {
					throw new SendMailException(e);
				}
				
			}
		}).start();
	}

	@Override
	public AccountBean login(String name, String password) {
		AccountBean result = null; 
		result = dao.findAccount(name, SHA1Utils.encoding(password));
		if (result == null)
			throw new AccountNotFoundException("账号或密码错误");
		if (!result.isActived())
			throw new AccountInactiveException("账号没有激活");
		return result;
	}

	@Override
	public boolean enableAccount(String code) {
		boolean result = false;
		AccountBean account = dao.findAccountByCode(code);
		if (account == null)
			throw new AccountActiveFailedException("无效的激活码");
		account.setActived(true);
		dao.updateAccount(account);
		return result;
	}

}
