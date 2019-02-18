#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Profile;

/**
 * Classe que realiza o Boot da aplicação Spring
 * Em ambientes JBoss
 * @author SEDES
 */
@SpringBootApplication
@Profile("jboss")
public class ExemploApplicationJBoss extends SpringBootServletInitializer {
    
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ExemploApplicationJBoss.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ExemploApplicationJBoss.class, args);
	}

}