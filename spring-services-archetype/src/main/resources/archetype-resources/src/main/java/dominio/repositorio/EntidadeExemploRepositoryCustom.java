#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dominio.repositorio;

import java.util.List;

import ${package}.dominio.modelo.EntidadeExemplo;

/**
 * Interface para uma consulta personalizada na entidade exemplo
 * que deverá ser estendida no repositório que será utilizado nos
 * services.
 * Deverá vir com o sufixo "Custom"
 * 
 * @author SEDES
 */
public interface EntidadeExemploRepositoryCustom {

	List<EntidadeExemplo> recuperarEntidadeExemploPor(String nome);
}
