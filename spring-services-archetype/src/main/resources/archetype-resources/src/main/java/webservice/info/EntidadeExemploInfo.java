#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.webservice.info;

import java.util.ArrayList;
import java.util.List;

import ${groupId}.base.infra.spring.webservice.info.Info;
import ${groupId}.base.infra.spring.webservice.info.Mapeador;
import ${package}.dominio.modelo.EntidadeExemplo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe que indica a representação do JSON que exporá o modelo para os
 * webservices
 * 
 * @author SEDES
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class EntidadeExemploInfo extends Info {

	private String nome;
	
	public EntidadeExemploInfo(EntidadeExemplo entidadeExemplo) {
	    super();
		setNome(entidadeExemplo.getNome());	
	}

	public <T> EntidadeExemploInfo(EntidadeExemplo entidadeExemplo, T metodoDoControlador) {
		super(metodoDoControlador);
		setNome(entidadeExemplo.getNome());
	}

}
