package com.learning.rough;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.learning.data.Constants;
import com.learning.email.java_Mail_API.TestConfig;

/**
 * https://www.codejava.net/java-ee/javamail/send-e-mail-with-attachment-in-java
 */
public class EmailAttachmentsSender_Rough {

	public static void sendEmailWithAttachments(String host, String port, final String userName, final String password,
			String[] toAddress, String subject, String message, String... attachFiles)
			throws AddressException, MessagingException {
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);
		// properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));

		InternetAddress[] addressTo = new InternetAddress[toAddress.length];
		for (int i = 0; i < toAddress.length; i++)
			addressTo[i] = new InternetAddress(toAddress[i]);
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		/*
		 * InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		 * msg.setRecipients(Message.RecipientType.TO, toAddresses);
		 */ msg.setSubject(subject);
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");

		// creates multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// adds attachments
		if (attachFiles != null && attachFiles.length > 0) {
			for (String filePath : attachFiles) {
				MimeBodyPart attachPart = new MimeBodyPart();

				try {
					attachPart.attachFile(filePath);
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				multipart.addBodyPart(attachPart);
			}
		}

		// sets the multi-part as e-mail's content
		msg.setContent(multipart);

		// sends the e-mail
		Transport.send(msg);

	}

	/**
	 * Test sending e-mail with attachments
	 */
	public static void main(String[] args) {
		// SMTP info
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = TestConfig.from;
		String password = TestConfig.password;

		// message info
		String mailTo = TestConfig.new_to;
		String subject = "New email with attachments";
		// String message = "I have some attachments for you.";
		String message = "<html>\r\n" + "\r\n" + " \r\n" + "\r\n"
				+ "        <body> \r\n<table class=\"container\" align=\"center\" style=\"padding-top:20px\">\r\n<tr align=\"center\"><td colspan=\"4\"><h2>"
				+ Constants.Project_Name + "</h2></td></tr>\r\n<tr><td>\r\n\r\n"
				+ "       <table style=\"background:#67c2ef;width:120px\" >\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">" + "7"
				+ "</td></tr>\r\n" + "                     <tr><td align=\"center\">Total</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                </td>\r\n" + "                <td>\r\n"
				+ "               \r\n" + "                 <table style=\"background:#79c447;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">" + "4"
				+ "</td></tr>\r\n" + "                     <tr><td align=\"center\">Passed</td></tr>\r\n"
				+ "       \r\n" + "                </table>\r\n" + "                </td>\r\n"
				+ "                <td>\r\n" + "                <table style=\"background:#ff5454;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">" + "2"
				+ "</td></tr>\r\n" + "                     <tr><td align=\"center\">Failed</td></tr>\r\n"
				+ "       \r\n" + "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
				+ "                <td>\r\n" + "                <table style=\"background:#fabb3d;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">" + "1"
				+ "</td></tr>\r\n" + "                     <tr><td align=\"center\">Skipped</td></tr>\r\n"
				+ "       \r\n" + "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
				+ "                </tr>\r\n" + "               \r\n" + "                \r\n"
				+ "            </table>\r\n" + "       \r\n" + "    </body>\r\n" + "</html>";

		// attachments
		// String[] attachFiles = new String[2];
		String attachFiles_1 = ".\\extent_reports\\All_Automation_Report_Fri_Sep_10_03_47_17_IST_2021.html";
		String attachFiles_2 = ".\\target\\surefire-reports\\emailable-report.html";

		try {
			sendEmailWithAttachments(host, port, mailFrom, password, TestConfig.to, subject, message, attachFiles_1,
					attachFiles_2);
			System.out.println("Email sent.");
		} catch (Exception ex) {
			System.out.println("Could not send email.");
			ex.printStackTrace();
		}
	}
}