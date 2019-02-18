#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.webservice.info;

import ${groupId}.base.infra.spring.webservice.info.Info;
import ${package}.dominio.modelo.SubElementoDespesa;
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
public class SubElementoDespesaInfo extends Info {

	private String nome;

	public SubElementoDespesaInfo(SubElementoDespesa subElementoDespesa) {
	    super();
		setNome(subElementoDespesa.getNome());
	}

	public <T> SubElementoDespesaInfo(SubElementoDespesa subElementoDespesa, T metodoDoControlador) {
		super(metodoDoControlador);
		setNome(subElementoDespesa.getNome());
	}

}
