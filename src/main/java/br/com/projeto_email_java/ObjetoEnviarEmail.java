package br.com.projeto_email_java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ObjetoEnviarEmail {
	
	private String userName = "alex.email.estudo@gmail.com";
	private String password= "cnhkdmmuccnofnbl";
	
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


	//Enviar email SEM anexo
	public void enviarEmail(boolean envioHtml) throws Exception {
		
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
		
		
		if(envioHtml) {
			message.setContent(textoEmail, "text/html; charset=UTF-8");
		} else {
			message.setText(textoEmail);/*Mensagem do email*/
		}
		
		
		Transport.send(message);	
	}
	
	//enviar email COM anexo
	public void enviarEmailAnexo(boolean envioHtml) throws Exception {
		
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
		
		/*1ª parte do e-mail que é o texto e a descrição do e-mail*/
		MimeBodyPart corpoEmail = new MimeBodyPart();
		
		if(envioHtml) {
			corpoEmail.setContent(textoEmail, "text/html; charset=UTF-8");
		} else {
			corpoEmail.setText(textoEmail);/*Mensagem do email*/
		}
		
		/*2ª parte do e-mail que é o anexo do e-mail em PDF ou qualquer outro tipo de arquivo*/
		MimeBodyPart anexoEmail = new MimeBodyPart();
		
		/*Onde é passado o simulador de PDF você pode passar seu arquivo gravado no Banco de Dados*/
		anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(simuladorDePdf(), "application/pdf")));
		anexoEmail.setFileName("anexoEmail.pdf");
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(corpoEmail);
		multipart.addBodyPart(anexoEmail);
		
		message.setContent(multipart);
		
		Transport.send(message);
	}
	
		//enviar email COM Lisya de anexos
		public void enviarEmailAnexoLista(boolean envioHtml) throws Exception {
			
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
			
			/*1ª parte do e-mail que é o texto e a descrição do e-mail*/
			MimeBodyPart corpoEmail = new MimeBodyPart();
			
			if(envioHtml) {
				corpoEmail.setContent(textoEmail, "text/html; charset=UTF-8");
			} else {
				corpoEmail.setText(textoEmail);/*Mensagem do email*/
			}
			
			List<FileInputStream> arquivos = new ArrayList<FileInputStream>();
			arquivos.add(simuladorDePdf());
			arquivos.add(simuladorDePdf());
			arquivos.add(simuladorDePdf());
			arquivos.add(simuladorDePdf());
			arquivos.add(simuladorDePdf());
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(corpoEmail);
			
			int index = 1;
			
			for (FileInputStream fileInputStream : arquivos) {
				
				/*2ª parte do e-mail que é o anexo do e-mail em PDF ou qualquer outro tipo de arquivo*/
				MimeBodyPart anexoEmail = new MimeBodyPart();
				
				/*Onde é passado o simulador de PDF você pode passar seu arquivo gravado no Banco de Dados*/
				anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream, "application/pdf")));
				anexoEmail.setFileName("anexoEmail"+index+".pdf");			
				
				multipart.addBodyPart(anexoEmail);
				
				index++;
			}
					
			message.setContent(multipart);
			
			Transport.send(message);
		}
	
	//Simulador de anexo em ODF
	private FileInputStream simuladorDePdf() throws Exception{
		
		Document document = new Document();
		File file = new File("fileAnexo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Teste de escrita simulada de PDF com o Java Mail"));
		document.close();
		
		return new FileInputStream(file);
		
	}

}
