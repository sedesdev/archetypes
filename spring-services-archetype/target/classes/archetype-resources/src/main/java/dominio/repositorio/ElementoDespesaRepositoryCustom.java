#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dominio.repositorio;

import java.util.List;

import ${package}.dominio.modelo.ElementoDespesa;

/**
 * Interface para uma consulta personalizada no Elemento Despesa
 * que deverá ser estendida no repositório que será utilizado nos
 * services.
 * Deverá vir com o sufixo "Custom"
 * 
 * @author SEDES
 */
public interface ElementoDespesaRepositoryCustom {

	List<ElementoDespesa> recuperarElementoDespesaPor(String nome);
}
