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
		
		StringBuilder stringBuilderTextoEmail = new StringBuilder();
		
		stringBuilderTextoEmail.append("Olá dileto Aluno, <br/><br/>");
		stringBuilderTextoEmail.append("Você está recebendo o acesso ao seu curso. <br/><br/>");
		stringBuilderTextoEmail.append("Para iniciar seu aprendizado, clique no botão abaixo e se divirta no processo. <br/><br/>");
		
		stringBuilderTextoEmail.append("<b>Login: </b> teste@teste.com.br<br/>");
		stringBuilderTextoEmail.append("<b>Senha: </> abcd,1234<br/><br/>");
		
		stringBuilderTextoEmail.append("<a target=\"_blank\" href=\"https://loiane.training/\" style=\"color:#2525a7; padding: 10px 15px; text-align:center; text-decoration: none; display:inline-block; border-radius: 25px; font-size: 12px; font-family:courier; border: 3px solid green; background-color:#99DA39; \">Acessar Portal do Aluno</a> <br/><br/>");
		
		stringBuilderTextoEmail.append("<span style=\"font-size:8px\">Ass.: Alex Ferreira Leite</span>");
		
		ObjetoEnviarEmail teste = new ObjetoEnviarEmail(
				"alex.f.leite@gmail.com", 
				"Alex Ferreira Leite",
				"Projeto JavaMail", 
				stringBuilderTextoEmail.toString()
		);
		
		teste.enviarEmail(true);
			
	}
}
