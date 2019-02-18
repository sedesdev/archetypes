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
 * Representa o JSON de lista de Elementos de Despesa
 * 
 * @author SEDES
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ElementosDespesaInfo extends Info {

	private List<ElementoDespesaInfo> list = new ArrayList<>();

	public ElementosDespesaInfo(List<ElementoDespesa> elementosDespesa) {
	    super();
		list.addAll(Mapeador.paraListaInfo(elementosDespesa, ElementoDespesaInfo.class));
	}

	public <T> ElementosDespesaInfo(List<ElementoDespesa> elementosDespesa, T metodoDoControlador) {
	    super(metodoDoControlador);
	    list.addAll(Mapeador.paraListaInfo(elementosDespesa, ElementoDespesaInfo.class, metodoDoControlador));
	}

}
