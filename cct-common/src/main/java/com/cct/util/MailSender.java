package com.cct.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
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
import javax.mail.util.ByteArrayDataSource;

import org.springframework.stereotype.Component;

import com.cct.model.Reporte;

@Component
public class MailSender {

	public boolean sendEmail(String to, byte[] bytes, Reporte reporte) {
		
		final String username = System.getenv("JAVA_MAIL_USERNAME");
		final String password = System.getenv("JAVA_MAIL_PASSWORD");
		final String host = System.getenv("JAVA_MAIL_HOST");

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "25");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(username));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("Reporte " + reporte.getIdReporte());

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			StringBuilder messageBuilder = new StringBuilder();
			messageBuilder.append("Reporte:\n\n");
			messageBuilder.append("ID: " + reporte.getIdReporte() + "\n");
			messageBuilder.append("Fecha: " + reporte.getCreationDate() + "\n");
			messageBuilder.append("Tipo: " + reporte.getTipo().name() + "\n");
			messageBuilder.append("MD5: " + reporte.getMd5());
			
			messageBodyPart.setText(messageBuilder.toString());

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = "reporte_" + reporte.getIdReporte() + ".pdf";
			DataSource source = new ByteArrayDataSource(bytes, "application/pdf");
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");
			return true;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}