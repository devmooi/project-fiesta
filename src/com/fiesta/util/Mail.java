package com.fiesta.util;

import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {

	public static void main(String[] args) {
		String randomPass = RandomPassword.randomPassword(8);
		System.out.println(randomPass);
		sendMail("ednadev@naver.com", randomPass);
	}
	
	public static void sendMail(String toEmail, String randomPass) {
		// 메일 인코딩
		final String bodyEncoding = "UTF-8"; //콘텐츠 인코딩
		
		String subject = "안녕하세요. 피에스타 입니다";
		String fromEmail = "puzzle.fiesta04@gmail.com";
		String fromUsername = "피에스타";
		
		final String username = "puzzle.fiesta04@gmail.com";
		final String password = "buqa dvap azdm rapy"; //발급받은 앱 비밀번호
		
		//메일에 출력할 텍스트
		StringBuffer sb = new StringBuffer();
		sb.append("<p>변경된 임시 비밀번호는 <span style='color: red;'>" + randomPass + "</span> 입니다.</p>");
		sb.append("<p>비밀번호 재변경 바랍니다.</p>");
		String html = sb.toString();
		
		//메일 옵션 설정
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "465");
	    props.put("mail.smtp.auth", "true");
	 
	    props.put("mail.smtp.quitwait", "false");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.socketFactory.fallback", "false");
	    
	    try {
	        // 메일 서버  인증 계정 설정
	        Authenticator auth = new Authenticator() {
	          protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	          }
	        };
	        
	        // 메일 세션 생성
	        Session session = Session.getInstance(props, auth);
	        
	        // 메일 송/수신 옵션 설정
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(fromEmail, fromUsername));
	        message.setRecipients(RecipientType.TO, InternetAddress.parse(toEmail, false));
	        message.setSubject(subject);
	        message.setSentDate(new Date());
	        
	        // 메일 콘텐츠 설정
	        Multipart mParts = new MimeMultipart();
	        MimeBodyPart mTextPart = new MimeBodyPart();
	        MimeBodyPart mFilePart = null;
	   
	        // 메일 콘텐츠 - 내용
	        mTextPart.setText(html, bodyEncoding, "html");
	        mParts.addBodyPart(mTextPart);
	              
	        // 메일 콘텐츠 설정
	        message.setContent(mParts);
	        
	        // MIME 타입 설정
	        MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
	        MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
	        MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
	        MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
	        MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
	        MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
	        CommandMap.setDefaultCommandMap(MailcapCmdMap);
	   
	        // 메일 발송
	        Transport.send( message );
	        
	      } catch ( Exception e ) {
	        e.printStackTrace();
	      }
	}
}
