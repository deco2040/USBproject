package jptflog.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	String mail_id = "jptflog@gmail.com";
	String mail_pw = "usbproject45";

	public Session setting() {
		Properties prop = System.getProperties();
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mail_id, mail_pw);
			}
		});
		
		return session;
	}
	
	public void sendMail(String email, String AuthenticationKey) {
		
		try {
			MimeMessage msg = new MimeMessage(setting());
			msg.setSentDate(new Date());

			msg.setFrom(new InternetAddress("jptflog@gmail.com", "jptflog"));
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);
			msg.setSubject("JPTflog 비밀번호 찾기 인증번호 메일", "UTF-8");
			msg.setText("안녕하세요 비밀번호를 찾기 위해서는 , 사이트에서 비밀번호 찾기에서 본 인증번호를 입력해 주세요." + " 인증번호: " + AuthenticationKey,
					"UTF-8");

			Transport.send(msg);

		} catch (AddressException ae) {
			System.out.println("AddressException : " + ae.getMessage());
		} catch (MessagingException me) {
			System.out.println("MessagingException : " + me.getMessage());
		} catch (UnsupportedEncodingException e) {
			System.out.println("UnsupportedEncodingException : " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
