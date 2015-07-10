package com.hm.util;

import java.io.File;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailSenderUtil {
	private static final Logger logger = LoggerFactory.getLogger(MailSenderUtil.class);
	public static MailSenderInfo mailInfo = null;
	public static final String EMAIL = "email";
	public static final String CONTENT = "content";
	public static final String SUBJECT = "subject";

	/**
	 * 以文本格式发送邮件
	 *
	 * @param mailInfo
	 *            待发送的邮件的信息
	 */
	public static boolean sendTextMail(Map<String, Object> map) {
		mailInfo = setMailInfo(map);
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate())
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MailSenderUtil().new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件格式
			BodyPart mdp = new MimeBodyPart();
			mdp.setContent(mailInfo.getContent(), "text/html;charset=utf-8");
			Multipart mm = new MimeMultipart();
			mm.addBodyPart(mdp);
			mailMessage.setContent(mm);
			// 设置邮件消息的主要内容
			// String mailContent = mailInfo.getContent();
			// mailMessage.setText(mailContent);
			mailMessage.saveChanges();
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 发送邮件
	 *
	 * @param to
	 *            接收人
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @return
	 */
	public static Boolean send(Map<String, Object> map, Vector<File> files) {
		mailInfo = setMailInfo(map);
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate())
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MailSenderUtil().new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件格式
			BodyPart mdp = new MimeBodyPart();
			mdp.setContent(mailInfo.getContent(), "text/html;charset=utf-8");
			Multipart mm = new MimeMultipart();
			mm.addBodyPart(mdp);
			if (!files.isEmpty()) {// 有附件
				Enumeration<File> efile = files.elements();
				while (efile.hasMoreElements()) {
					mdp = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(efile.nextElement().toString()); // 得到数据源
					mdp.setDataHandler(new DataHandler(fds)); // 得到附件本身并至入BodyPart
					mdp.setFileName(fds.getName()); // 得到文件名同样至入BodyPart
					mm.addBodyPart(mdp);
				}
				files.removeAllElements();
			}
			mailMessage.setContent(mm);
			mailMessage.saveChanges();
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	public static MailSenderInfo setMailInfo(Map<String, Object> map) {
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderUtil().new MailSenderInfo();
		CommonParam param = new CommonParam();
		mailInfo.setMailServerHost(param.getString("MAIL_SERVER_HOST"));
		mailInfo.setMailServerPort(param.getString("MAIL_POST"));
		mailInfo.setValidate(true);
		mailInfo.setUserName(param.getString("MAIL_USERNAME"));
		mailInfo.setPassword(param.getString("MAIL_PASSWORD"));
		mailInfo.setFromAddress(param.getString("MAIL_FROM_ADDRESS"));
		mailInfo.setToAddress((String) map.get(EMAIL));
		mailInfo.setSubject((String) map.get(SUBJECT));
		mailInfo.setContent((String) map.get(CONTENT));
		return mailInfo;
	}

	class MyAuthenticator extends Authenticator {
		String userName = null;
		String password = null;

		public MyAuthenticator() {
		}

		public MyAuthenticator(String username, String password) {
			userName = username;
			this.password = password;
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userName, password);
		}
	}

	class MailSenderInfo {
		// 发送邮件的服务器的IP和端口
		private String mailServerHost;
		private String mailServerPort;
		// 邮件发送者的地址
		private String fromAddress;
		// 邮件接收者的地址
		private String toAddress;
		// 登陆邮件发送服务器的用户名和密码
		private String userName;
		private String password;
		// 是否需要身份验证
		private boolean validate = false;
		// 邮件主题
		private String subject;
		// 邮件的文本内容
		private String content;
		// 邮件附件的文件名
		private String[] attachFileNames;

		/**
		 * 获得邮件会话属性
		 */
		public Properties getProperties() {
			Properties p = new Properties();
			p.put("mail.smtp.host", mailServerHost);
			p.put("mail.smtp.port", mailServerPort);
			p.put("mail.smtp.auth", validate ? "true" : "false");
			return p;
		}

		public String getMailServerHost() {
			return mailServerHost;
		}

		public void setMailServerHost(String mailServerHost) {
			this.mailServerHost = mailServerHost;
		}

		public String getMailServerPort() {
			return mailServerPort;
		}

		public void setMailServerPort(String mailServerPort) {
			this.mailServerPort = mailServerPort;
		}

		public boolean isValidate() {
			return validate;
		}

		public void setValidate(boolean validate) {
			this.validate = validate;
		}

		public String[] getAttachFileNames() {
			return attachFileNames;
		}

		public void setAttachFileNames(String[] fileNames) {
			attachFileNames = fileNames;
		}

		public String getFromAddress() {
			return fromAddress;
		}

		public void setFromAddress(String fromAddress) {
			this.fromAddress = fromAddress;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getToAddress() {
			return toAddress;
		}

		public void setToAddress(String toAddress) {
			this.toAddress = toAddress;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String textContent) {
			content = textContent;
		}
	}
}
