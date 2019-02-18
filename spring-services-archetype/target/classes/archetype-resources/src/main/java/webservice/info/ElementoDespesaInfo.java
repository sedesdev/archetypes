#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.webservice.info;

import java.util.ArrayList;
import java.util.List;

import ${groupId}.base.infra.spring.webservice.info.Info;
import ${groupId}.base.infra.spring.webservice.info.Mapeador;
import ${package}.dominio.modelo.ElementoDespesa;
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
public class ElementoDespesaInfo extends Info {

	private String nome;
	private List<SubElementoDespesaInfo> subElementos = new ArrayList<>();

	public ElementoDespesaInfo(ElementoDespesa elementoDespesa) {
	    super();
		setNome(elementoDespesa.getNome());
		subElementos = Mapeador.paraListaInfo(elementoDespesa.getSubElementos(), SubElementoDespesaInfo.class);
	}

	public <T> ElementoDespesaInfo(ElementoDespesa elementoDespesa, T metodoDoControlador) {
		super(metodoDoControlador);
		setNome(elementoDespesa.getNome());
		subElementos = Mapeador.paraListaInfo(elementoDespesa.getSubElementos(), SubElementoDespesaInfo.class);
	}

}
