package br.com.projeto_email_java;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	private String userName = "alex.java.teste@gmail.com";
	private String password= "wmitxuyewrrqkvdp";
	
	@org.junit.Test
	public void testeEmail() {
		
	try {
		
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");/*Autorização*/
		properties.put("mail.smtp.starttls", "true");/*Autenticação*/
		properties.put("mail.smtp.host", "smtp.gmail.com");/*Servidor Gamil Google*/
		properties.put("mail.smtp.port", "465");/*Porta do Servidor*/
		properties.put("mail.smtp.socketFactory.port", "465");/*Especifica a porta a ser conectada pelo socket*/
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/*Classe socket de conexão ao SMTP*/
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
		
		System.out.println("Logging realizado com sucesso!");
		
		Address[] toUser = InternetAddress.parse("alex.f.leite@gmail.com, alexleitecc@gmail.com, alex.java.teste@gmail.com");
		
		Message message = new MimeMessage(session);
		
		message.setFrom(new InternetAddress(userName));/*Quem está enviando*/
		message.setRecipients(Message.RecipientType.TO, toUser);/*Lista de emails de destino*/
		message.setSubject("Email de Teste de Envio de através do JavaMail");/*Assunto do email*/
		message.setText("Teste de envio de email atrvaés do Java pela biblioteca javax.mail.");
		message.setText("Obrigado por aceitar receber este email de este");
		message.setText("Cordialemnte,");
		message.setText("Alex Ferreira Leite");
		
		Transport.send(message);
		
		
	} catch (Exception e) {
		 
		e.printStackTrace();
		
	}	
			
	}
}
