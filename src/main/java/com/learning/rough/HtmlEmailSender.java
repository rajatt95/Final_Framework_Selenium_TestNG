package com.learning.rough;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.learning.data.Constants;
import com.learning.email.java_Mail_API.TestConfig;

public class HtmlEmailSender {

	public void sendHtmlEmail(String host, String port, final String userName, final String password, String toAddress,
			String subject, String message) throws AddressException, MessagingException {

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

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
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		// set plain text message
		msg.setContent(message, "text/html");

		// sends the e-mail
		Transport.send(msg);

	}

	/**
	 * Test the send html e-mail method
	 *
	 */
	public static void main(String[] args) {
		// SMTP server information
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = TestConfig.from;
		String password = TestConfig.password;

		// outgoing message information
		String mailTo = TestConfig.new_to;
		String subject = "Hello my friend - HtmlEmailSender";

		// message contains HTML markups
		/*
		 * String message = "<i>Greetings!</i><br>"; message +=
		 * "<b>Wish you a nice day!</b><br>"; message += "<font color=red>Duke</font>";
		 */
		/*
		 * String message = "<table><tr><td><h2>Total</h2></td>"; message +=
		 * "<td><h2>Pass</h2></td>"; message +=
		 * "<td><h2><font color=red>Fail</font></h2></td>"; message +=
		 * "<td><h2><font color=red>SKip</font></h2></td></tr></table>";
		 */

		String message2 = "<html>\r\n" + "\r\n" + "\r\n" + "        <body>\r\n"
				+ "<h4>Environment: ${​​​​​​​Environment}​​​​​​​ </h4>\r\n"
				+ "          <table class=\"container\" align=\"center\" style=\"padding-top:20px\">\r\n"
				+ "      <tr align=\"center\"><td colspan=\"4\"><h2>" + Constants.Project_Name + "</h2></td></tr>\r\n"
				+ "            <tr><td>\r\n" + "               \r\n"
				+ "                 <table style=\"background:#67c2ef;width:120px\" >\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">${​​​​​​​TEST_COUNTS,var=\"total\"}​​​​​​​</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Total</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                </td>\r\n" + "                <td>\r\n"
				+ "               \r\n" + "                 <table style=\"background:#79c447;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">${​​​​​​​TEST_COUNTS,var=\"pass\"}​​​​​​​</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Passed</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                </td>\r\n" + "                <td>\r\n"
				+ "                <table style=\"background:#ff5454;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">${​​​​​​​TEST_COUNTS,var=\"fail\"}​​​​​​​</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Failed</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
				+ "                <td>\r\n" + "                <table style=\"background:#fabb3d;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">${​​​​​​​TEST_COUNTS,var=\"skip\"}​​​​​​​</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Skipped</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
				+ "                </tr>\r\n" + "               \r\n" + "                \r\n"
				+ "            </table>\r\n" + "       \r\n" + "    </body>\r\n" + "</html>\r\n" + "\r\n" + "\r\n"
				+ "\r\n" + "";
		String message1 = "<html>\r\n" + "\r\n" + " \r\n" + "\r\n" + "        <body> \r\n"
				+ "                 <table style=\"background:#67c2ef;width:120px\" >\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">7</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Total</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                </td>\r\n" + "                <td>\r\n"
				+ "               \r\n" + "                 <table style=\"background:#79c447;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">4</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Passed</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                </td>\r\n" + "                <td>\r\n"
				+ "                <table style=\"background:#ff5454;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">2</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Failed</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
				+ "                <td>\r\n" + "                <table style=\"background:#fabb3d;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">1</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Skipped</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
				+ "                </tr>\r\n" + "               \r\n" + "                \r\n"
				+ "            </table>\r\n" + "       \r\n" + "    </body>\r\n" + "</html>";

		HtmlEmailSender mailer = new HtmlEmailSender();

		try {
			mailer.sendHtmlEmail(host, port, mailFrom, password, mailTo, subject, message1);
			System.out.println("Email sent.");
		} catch (Exception ex) {
			System.out.println("Failed to sent email.");
			ex.printStackTrace();
		}
	}
}