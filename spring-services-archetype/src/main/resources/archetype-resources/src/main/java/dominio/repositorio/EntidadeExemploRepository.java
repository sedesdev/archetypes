#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dominio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ${package}.dominio.modelo.EntidadeExemplo;


/**
 * Interface que fará acesso aos dados.
 * Deve estender apenas interfaces
 * @author SEDES
 */
public interface EntidadeExemploRepository extends JpaRepository<EntidadeExemplo, Long>, EntidadeExemploRepositoryCustom  {
	
	// Exemplo de consulta personaliza por apenas query
	@Query(value = "SELECT e FROM EntidadeExemplo e")
	List<EntidadeExemplo> recuperarTodos();

	@Query(value = "SELECT e FROM EntidadeExemplo e where lower(e.nome) like '%' || lower(:nome) || '%'")
    List<EntidadeExemplo> recuperarPeloNome(@Param("nome") String nome);

}
