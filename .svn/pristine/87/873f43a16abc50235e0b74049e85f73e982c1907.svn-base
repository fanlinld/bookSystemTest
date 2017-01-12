package com.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author ydd
 *
 */
public class MailUtil {
	
	/**
	 * 发送邮件
	 * @param subj 发送邮件标题
	 * @param text 发送邮件内容
	 * @param to   接收人
	 * @return
	 */
	public static boolean sendMail(String subj,String text,String to){
		PropertiesUtil.readValue("OcrImage_Path");		
		String host   = PropertiesUtil.readValue("mail.smtp.host").toString();
		int    port   = Integer.parseInt(PropertiesUtil.readValue("mail.smtp.port"));
		String user   = PropertiesUtil.readValue("mail.smtp.user").toString();
		String passwd = PropertiesUtil.readValue("mail.smtp.passwd").toString();
		//发信人
		String from = PropertiesUtil.readValue("from").toString();
		//转发接收人
		String cc ="";
		//昵称
		String nick = PropertiesUtil.readValue("nick").toString();
		return sendMessage(to, cc, from, subj, text, user, passwd, host, port,nick);
	}
	
	/**
	 * 发送邮件消息
	 * @param to 邮件接收人
	 * @param cc 转发接收人
	 * @param from 发件人
	 * @param subj 邮件标题
	 * @param text 邮件内容
	 * @param login 发件帐号
	 * @param password 发件密码
	 * @param smtphost 发件服务器
	 * @param port 服务端口
	 * @nick 昵称
	 * @return
	 */
	private static boolean sendMessage(String to, String cc, String from, String subj, String text,
			final String login, final String password, String smtphost, int port,String nick){
		boolean ok = false;
		String logTo = "";
		try {
			// general
			Properties props = new Properties();
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.transport.protocol", "smtp");
			Session session = Session.getDefaultInstance(props);
			session.setDebug(true);
			// content settings
			Message msg = new MimeMessage(session);
			msg.setContent("Content-Transfer-Encoding", "utf-8");
			msg.setContent("Content-Type", "text/html;charset=utf-8");
			// from
			//msg.setFrom(new InternetAddress(from));
			// to
			InternetAddress[] toAddresses = new InternetAddress[1];
			//for (int i = 0; i < toArray.size(); i++) {
				toAddresses[0] = new InternetAddress(to);
				logTo = logTo + "to=" + to + ";";
			//}
			msg.addRecipients(Message.RecipientType.TO, toAddresses);
			//昵称
            try {  
                nick=javax.mail.internet.MimeUtility.encodeText(nick);  
            } catch (UnsupportedEncodingException e) {  
                e.printStackTrace();  
            } 
            msg.setFrom(new InternetAddress (from,nick)); 
			// cc
			//InternetAddress[] ccAddresses = new InternetAddress[1];
			//for (int i = 0; i < ccArray.size(); i++) {
				//ccAddresses[0] = new InternetAddress(cc);
				//logTo = logTo + "cc=" + cc + ";";
			//}
			//msg.addRecipients(Message.RecipientType.CC, ccAddresses);
			// body
			msg.setText(text);
			// subject
			msg.setSubject(subj);
			// do the hard work
			Transport transport = session.getTransport();
			transport.connect(smtphost, port, login, password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			ok = true;
		} catch (AuthenticationFailedException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

}
