#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dominio.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import ${groupId}.base.infra.spring.email.EmailServiceImpl;

@Component
public class EmailService extends EmailServiceImpl {

	public EmailService(@Autowired JavaMailSender sender) {
		super(sender);
	}

}
