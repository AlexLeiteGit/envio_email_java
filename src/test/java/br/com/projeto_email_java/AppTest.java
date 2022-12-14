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
	
	@org.junit.Test
	public void testeEmail() throws Exception {
		
		ObjetoEnviarEmail teste = new ObjetoEnviarEmail(
				"alex.f.leite@gmail.com", 
				"Alex Ferreira Leite", 
				"Testando Classe de Envio de Email em Java", 
				"Obrigado por receber este eamil de teste!"
		);
		
		teste.enviarEmail();
			
	}
}
