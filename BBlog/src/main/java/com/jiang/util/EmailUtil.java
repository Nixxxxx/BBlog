package com.jiang.util;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * 发送邮件
 * 
 * @author JH
 *
 */
public class EmailUtil {
	public static final String HOST = "smtp.exmail.qq.com"; // 邮件服务器地址
	public static final String PROTOCOL = "smtp"; // 邮件协议
	public static final int PORT = 465; // 邮箱端口号
	public static final String FROM = "jh@stu.csust.edu.cn"; // 发件人的email地址
	public static final String PWD = "Fantastic091"; // 发件人密码

	/**
	 * 获取Session
	 * 
	 * @return
	 * @throws GeneralSecurityException
	 */
	private static Session getSession(){
		Properties props = System.getProperties();
		props.put("mail.smtp.host", HOST); // 设置服务器地址
		props.put("mail.store.protocol", PROTOCOL); // 设置协议
		props.put("mail.smtp.port", PORT); // 设置端口
		props.put("mail.smtp.auth", "true");
		MailSSLSocketFactory sf;
		try {
			sf = new MailSSLSocketFactory();  // SSL加密（部分邮箱 例如腾讯）
			sf.setTrustAllHosts(true);
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.socketFactory", sf);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} 

		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM, PWD);
			}
		};
		Session session = Session.getDefaultInstance(props, authenticator);

		return session;
	}

	/**
	 * 发送简单邮件
	 * 
	 * @param toEmail
	 * @param content
	 * @throws GeneralSecurityException
	 */
	public static void sendEmail(String toEmail, String content) {
		Session session = getSession();
		try {
			System.out.println("--send--" + content);

			Message msg = new MimeMessage(session); // Instantiate a message
			msg.setFrom(new InternetAddress(FROM)); //
			InternetAddress[] address = { new InternetAddress(toEmail) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject("账号激活邮件");
			msg.setSentDate(new Date());
			msg.setContent(content, "text/html;charset=utf-8");

			// Send the message
			Transport.send(msg);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	
	/**
	 * 发送带附件邮件
	 * @param toEmail
	 * @param filename
	 */
	public static void sendEmailWithFile(String toEmail, String filename) {
		Session session = getSession();
		try {
			// 创建默认的 MimeMessage 对象。
			MimeMessage message = new MimeMessage(session);
			// Set From: 头部头字段
			message.setFrom(new InternetAddress(FROM));
			// Set To: 头部头字段
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			// Set Subject: 头字段
			message.setSubject("博客日志文件");
			// 创建消息部分
			BodyPart messageBodyPart = new MimeBodyPart();
			// 创建多重消息
			Multipart multipart = new MimeMultipart();

			// 附件部分
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);
			
			message.setContent(multipart);
			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
