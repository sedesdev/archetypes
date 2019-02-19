#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.webservice.info;

import java.util.ArrayList;
import java.util.List;

import ${package}.base.infra.spring.webservice.info.Info;
import ${package}.base.infra.spring.webservice.info.Mapeador;
import ${package}.${artifactId}.dominio.modelo.EntidadeExemplo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Representa o JSON de lista de entidades exemplo
 * 
 * @author SEDES
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class EntidadesExemploInfo extends Info {

	private List<EntidadeExemploInfo> list = new ArrayList<>();

	public EntidadesExemploInfo(List<EntidadeExemplo> entidadesExemplo) {
	    super();
		list.addAll(Mapeador.paraListaInfo(entidadesExemplo, EntidadeExemploInfo.class));
	}

	public <T> EntidadesExemploInfo(List<EntidadeExemplo> entidadesExemplo, T metodoDoControlador) {
	    super(metodoDoControlador);
	    list.addAll(Mapeador.paraListaInfo(entidadesExemplo, EntidadeExemploInfo.class, metodoDoControlador));
	}

}
