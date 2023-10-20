package com.rsecinformation;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class AppTest {

  @Test
  public void testeEmail() {

    final String username = "security@abc.com";
    final String password = "";

    Properties props = new Properties();
    props.put("mail.smtp.host", "mail.abc.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
      @Override
      protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
        return new javax.mail.PasswordAuthentication(username, password);
      }
    });

    try {

      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(username));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("fernandoribeiror@yahoo.com.br"));
      message.setSubject("Receitas de Código: Assunto do E-mail");
      message.setText("Olá.!! Esta e-mail foi enviado usando javamail");

      Transport.send(message);

      System.out.println("Pronto!");

    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }
}
