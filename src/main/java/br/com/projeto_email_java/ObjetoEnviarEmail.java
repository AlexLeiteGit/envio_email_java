package br.com.projeto_email_java;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ObjetoEnviarEmail {
	
	private String userName = "alex.java.teste@gmail.com";
	private String password= "wmitxuyewrrqkvdp";
	
	private String listaDestinatarios = "";
	private String nomeRemetente = "";
	private String assuntoEmail = "";
	private String textoEmail = "";
	
	
	
	public ObjetoEnviarEmail(String listaDestinatarios, String nomeRemetente, String assuntoEmail, String textoEmail) {
		super();
		this.listaDestinatarios = listaDestinatarios;
		this.nomeRemetente = nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;
	}



	public void enviarEmail() throws Exception {
		
		Properties properties = new Properties();
		
		properties.put("mail.smtp.ssl.trust", "*");/*Protocolo de segurança ssl autenticando qualqeur palavra*/
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
		
		Address[] toUser = InternetAddress.parse(listaDestinatarios);
		
		Message message = new MimeMessage(session);
		
		message.setFrom(new InternetAddress(userName, nomeRemetente));/*Quem está enviando*/
		message.setRecipients(Message.RecipientType.TO, toUser);/*Lista de emails de destino*/
		message.setSubject(assuntoEmail);/*Assunto do email*/
		message.setText(textoEmail);/*Mensagem do email*/
		
		Transport.send(message);
		
	}

}
