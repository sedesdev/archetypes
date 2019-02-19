#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

/**
 * Classe que realiza o Boot da aplicação Spring
 * em ambiente de DESENVOLVIMENTO
 * 
 * @author SEDES
 */
@SpringBootApplication
@Profile("dev")
public class AppDesenv  {

	public static void main(String[] args) {
	    SpringApplication.run(AppDesenv.class, args);
	}

}